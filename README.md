# newChess
Sistema distribuido de xadrez 

📦 regras_xadrez/
   ├── get_valid_moves()
   ├── is_checkmate()
   └── aplicar_jogada()

📦 game-service/
   └── usa regras_xadrez

📦 bot-service/
   └── usa regras_xadrez + algoritmo minimax

Rotas:
   └── Chat:
      └── /ws/{match_id}/{player_id}   (Abre coneção com o chat via web socket. Ex: ws://localhost:8000/ws/match1/player2)
   └── Match Finder:
      └──
