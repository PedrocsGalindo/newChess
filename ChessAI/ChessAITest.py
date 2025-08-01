import requests
import json

IA_URL = "http://localhost:8000/best-move"

tabuleiro_exemplo = [
    "WP2a", "WP2b", "WP2c",
    "BP7a", "BP7b", "BP7c",
    "WK1e", "BK8e"
]

cor = "BRANCO"

payload = {
    "tabuleiro": tabuleiro_exemplo,
    "cor": cor
}

resposta = requests.post(
    IA_URL,
    headers={"Content-Type": "application/json"},
    data=json.dumps(payload)
)

print("Status:", resposta.status_code)
try:
    print("Resposta JSON:", resposta.json())
except Exception as e:
    print("Erro ao ler JSON:", e)
    print("Texto da resposta:", resposta.text)
