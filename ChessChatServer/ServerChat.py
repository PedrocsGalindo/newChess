from fastapi import FastAPI, WebSocket, WebSocketDisconnect
from collections import defaultdict
import uvicorn

app = FastAPI()

# Dicionário para guardar as conexões por partida
# Ex: rooms["match1"] = [websocket1, websocket2]
rooms = defaultdict(list)

@app.websocket("/ws/{match_id}/{player_id}")
async def websocket_endpoint(websocket: WebSocket, match_id: str, player_id: str):
    await websocket.accept()
    rooms[match_id].append(websocket)
    
    try:
        while True:
            data = await websocket.receive_text()
            # Envia a mensagem para todos na sala
            for conn in rooms[match_id]:
                if conn != websocket:  # Não envia de volta para quem enviou
                    await conn.send_text(f"{player_id}: {data}")
    except WebSocketDisconnect:
        rooms[match_id].remove(websocket)
        if not rooms[match_id]:
            del rooms[match_id]

if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=8000)
