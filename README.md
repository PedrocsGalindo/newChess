# newChess

Sistema distribuído de xadrez

---

## Estrutura dos módulos

📦 **regras_xadrez/**  
- `get_valid_moves()`  
- `is_checkmate()`  
- `aplicar_jogada()`

📦 **game-service/**  
- Usa `regras_xadrez`

📦 **bot-service/**  
- Usa `regras_xadrez` + algoritmo minimax

---

## Rotas da API

### Chat  
- **WebSocket:** `/ws/{match_id}/{player_id}`  
  Exemplo: `ws://localhost:8000/ws/match1/player2`  
  (Abre conexão com o chat via WebSocket)

### Match Finder  
