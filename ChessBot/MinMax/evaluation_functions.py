from ChessRules_API import pecas_em_jogo

scoring= {
          'P': 1,
          'N': 3,
          'B': 3,
          'R': 5,
          'Q': 9,
          'K': 0,
          }

# tem q ter uma forma de saber a cor
class Evaluation():
    def simple(BOARD, bot_color):
        """
        bot_color -> B or W
        """
        score = 0
        pieces = BOARD.piece_map()
        for piece in pieces:
            if (piece[0].upper() == bot_color):
                score += scoring[str(piece[1])]
            else:
                score -= scoring[str(piece[1])]

        return score
