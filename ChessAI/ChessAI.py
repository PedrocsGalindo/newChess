from fastapi import FastAPI
from pydantic import BaseModel
import requests
import copy

RULES_SERVER = "http://localhost:8080"

class IARequest(BaseModel):
    tabuleiro: list[str]  # Lista de strings como ["WR1a", "WN2a", ...]
    cor: str              # "BRANCO" ou "PRETO"

class IAResponse(BaseModel):
    origem: str
    destino: str

app = FastAPI(title="Chess AI Service")

PIECE_VALUES = {
    "P": 1,    # Peão
    "N": 3,    # Cavalo
    "B": 3,    # Bispo
    "R": 5,    # Torre
    "Q": 9,    # Rainha
    "K": 1000  # Rei
}

def evaluate_position(tabuleiro: list[str], cor: str) -> int:
    """Avalia o tabuleiro pelo valor do material."""
    score = 0
    for piece in tabuleiro:
        color = piece[0]  # 'W' ou 'B'
        tipo = piece[1]   # 'P', 'N', etc.
        valor = PIECE_VALUES.get(tipo, 0)
        if color == 'W':
            score += valor
        else:
            score -= valor
    return score if cor == "BRANCO" else -score


def opponent(cor: str) -> str:
    return "PRETO" if cor == "BRANCO" else "BRANCO"


def get_all_moves(tabuleiro: list[str], cor: str) -> dict:
    """
    Consulta o módulo de regras para obter todas as jogadas possíveis.
    Retorna dict {origem: [destinos]}.
    """
    resp = requests.get(
        f"{RULES_SERVER}/ChessRules/todasJogadasPossiveis",
        json={"tabuleiro": tabuleiro, "cor": cor}
    )
    resp.raise_for_status()
    return resp.json().get("msg", {})


def simulate_move(tabuleiro: list[str], origem: str, destino: str) -> list[str]:
    novo_tabuleiro = copy.deepcopy(tabuleiro)

    # Encontrar peça na posição de origem
    peca = None
    for i, pos in enumerate(novo_tabuleiro):
        if pos[2:] == origem:
            peca = novo_tabuleiro.pop(i)
            break

    # Remove peça capturada na posição de destino
    novo_tabuleiro = [p for p in novo_tabuleiro if p[2:] != destino]

    # Move a peça para o destino
    if peca:
        peca = peca[:2] + destino
        novo_tabuleiro.append(peca)

    return novo_tabuleiro


def verificar_estado(tabuleiro: list[str], cor: str) -> str:
    tabuleiro_str = ",".join(tabuleiro)
    resp = requests.get(
        f"{RULES_SERVER}/ChessRules/verificarEstado",
        params={"tabuleiro": tabuleiro_str, "cor": cor}
    )
    resp.raise_for_status()
    return resp.json().get("msg", "ANDAMENTO")

def minimax(tabuleiro, cor, profundidade, alpha, beta, maximizing_player):
    if profundidade == 0:
        return evaluate_position(tabuleiro, cor), None

    estado = verificar_estado(tabuleiro, cor if maximizing_player else opponent(cor))
    if estado != "ANDAMENTO":
        return evaluate_position(tabuleiro, cor), None

    jogadas = get_all_moves(tabuleiro, cor if maximizing_player else opponent(cor))
    if not jogadas:
        return evaluate_position(tabuleiro, cor), None

    melhor_jogada = None

    if maximizing_player:
        max_eval = float("-inf")
        for origem, destinos in jogadas.items():
            for destino in destinos:
                novo_tabuleiro = simulate_move(tabuleiro, origem, destino)
                eval_score, _ = minimax(novo_tabuleiro, cor, profundidade-1, alpha, beta, False)
                if eval_score > max_eval:
                    max_eval = eval_score
                    melhor_jogada = (origem, destino)
                alpha = max(alpha, eval_score)
                if beta <= alpha:
                    break
        return max_eval, melhor_jogada
    else:
        min_eval = float("inf")
        for origem, destinos in jogadas.items():
            for destino in destinos:
                novo_tabuleiro = simulate_move(tabuleiro, origem, destino)
                eval_score, _ = minimax(novo_tabuleiro, cor, profundidade-1, alpha, beta, True)
                if eval_score < min_eval:
                    min_eval = eval_score
                    melhor_jogada = (origem, destino)
                beta = min(beta, eval_score)
                if beta <= alpha:
                    break
        return min_eval, melhor_jogada


# Endpoint principal
@app.post("/best-move", response_model=IAResponse)
def get_best_move(req: IARequest):
    _, jogada = minimax(req.tabuleiro, req.cor, profundidade=2, alpha=float("-inf"), beta=float("inf"), maximizing_player=True)
    if jogada:
        return IAResponse(origem=jogada[0], destino=jogada[1])
    return IAResponse(origem="", destino="")
