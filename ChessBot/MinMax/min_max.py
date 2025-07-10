import random
from ChessRules_API import todas_jogadas_possiveis, mover_peca
from MinMax.evaluation_functions import Evaluation

def random_agent(BOARD):
    return random.choice(list(todas_jogadas_possiveis(BOARD)))


def min_maxN(BOARD, f, N = 5):
    """
        f:
        Evaluation.simple
        Evaluation.mediumn
        Evaluation.Hard
    """
    moves = list(todas_jogadas_possiveis(BOARD))
    scores = []

    # retorno da API é um dicionario ate agora
    for  p, np in moves:
        new_board = mover_peca(BOARD, p, np)

        if N>1:
            temp_best_move = min_maxN(new_board,N-1, f)
            new_board.push(temp_best_move)

        scores.append(f(new_board))

    if BOARD.turn == True:
       
        best_move = moves[scores.index(max(scores))]

    else:
        best_move = moves[scores.index(min(scores))]

    return best_move