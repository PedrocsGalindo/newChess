# newChess
Sistema distribuido de chadrez 

📦 regras_xadrez/
   ├── get_valid_moves()
   ├── is_checkmate()
   └── aplicar_jogada()

📦 game-service/
   └── usa regras_xadrez

📦 bot-service/
   └── usa regras_xadrez + algoritmo minimax