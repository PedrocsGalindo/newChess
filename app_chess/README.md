## Camada de Serviço
Exponibiliza APIs públicas para outras partes do sistema (por exemplo, REST endpoints ou métodos RMI).

JogoService: inicializar partida, validar jogada, obter estado do tabuleiro.

## Camada de Modelo
Contém as classes que representam o domínio:

Tabuleiro

Peca e subclasses (Torre, Cavalo...)

Jogador

Jogada

EstadoDaPartida

## Camada de Utilitários
Métodos auxiliares:

Geração de movimentos válidos.

Validação de cheque e xeque-mate.

Conversão de posições.