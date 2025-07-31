from fastapi import FastAPI
from pydantic import BaseModel
import requests
import copy

# =====================
# CONFIG
# =====================
RULES_SERVER = "http://localhost:8080"  # URL do módulo Java/Spring

# =====================
# MODELOS DE REQUISIÇÃO
# =====================
class IARequest(BaseModel):
    tabuleiro: list[str]
    cor: str

class IAResponse(BaseModel):
    origem: str
    destino: str

# =====================
# FASTAPI APP
# =====================
app = FastAPI(title="Chess AI Service")

# =====================
# FUNÇÃO DE AVALIAÇÃO SIMPLES
# =====================
PIECE_VALUES = {
    "P": 1,
    "N": 3,
    "B": 3,
    "R": 5,
    "Q": 9,
    "K": 1000
}

def evaluate_position(tabuleiro: list[str], cor: str) -> int:
    """Avalia o tabuleiro com base no valor do material."""
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

# =====================
# MINIMAX COM ALFA-BETA
# =====================
def minimax(tabuleiro, cor, profundidade, alpha, beta, maximizing_player):
    if profundidade == 0:
        return evaluate_position(tabuleiro, cor), None

    # Pega movimentos possíveis
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

# =====================
# FUNÇÕES AUXILIARES
# =====================
def opponent(cor):
    return "PRETO" if cor == "BRANCO" else "BRANCO"

def get_all_moves(tabuleiro, cor):
    """Consulta o módulo de regras para obter todas as jogadas possíveis."""
    resp = requests.get(
        f"{RULES_SERVER}/ChessRules/TodasJogadasPossiveis",
        json={"Tabuleiro": tabuleiro, "Cor": cor}
    )
    return resp.json().get("msg", {})

def simulate_move(tabuleiro, origem, destino):
    """Cria um novo tabuleiro aplicando uma jogada."""
    novo_tabuleiro = copy.deepcopy(tabuleiro)
    # Remove peça da origem
    peca = None
    for i, pos in enumerate(novo_tabuleiro):
        if pos[2:] == origem:
            peca = novo_tabuleiro.pop(i)
            break
    # Remove peça capturada (se houver) no destino
    novo_tabuleiro = [p for p in novo_tabuleiro if p[2:] != destino]
    # Coloca peça na nova posição
    if peca:
        peca = peca[:2] + destino
        novo_tabuleiro.append(peca)
    return novo_tabuleiro

# =====================
# ENDPOINT PRINCIPAL
# =====================
@app.post("/best-move", response_model=IAResponse)
def get_best_move(req: IARequest):
    _, jogada = minimax(req.tabuleiro, req.cor, profundidade=2, alpha=float("-inf"), beta=float("inf"), maximizing_player=True)
    if jogada:
        return IAResponse(origem=jogada[0], destino=jogada[1])
    return IAResponse(origem="", destino="")
