import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Piece } from 'src/app/models/piece';
import { ChessService } from 'src/app/services/chess.service';

@Component({
  standalone: true,
  imports: [CommonModule],
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.scss']
})
export class BoardComponent implements OnInit {
  selectedPosition: { row: number; col: number } | null = null;
  rows = Array(8).fill(0);
  cols = Array(8).fill(0);
  board: (Piece | null)[][] = [
    [ { type: 'rook', color: 'black' }, { type: 'knight', color: 'black' }, { type: 'bishop', color: 'black' }, { type: 'queen', color: 'black' }, { type: 'king', color: 'black' }, { type: 'bishop', color: 'black' }, { type: 'knight', color: 'black' }, { type: 'rook', color: 'black' } ],
    [ { type: 'pawn', color: 'black' }, { type: 'pawn', color: 'black' }, { type: 'pawn', color: 'black' }, { type: 'pawn', color: 'black' }, { type: 'pawn', color: 'black' }, { type: 'pawn', color: 'black' }, { type: 'pawn', color: 'black' }, { type: 'pawn', color: 'black'} ],
    [ null, null, null, null, null, null, null, null ],
    [ null, null, null, null, null, null, null, null ],
    [ null, null, null, null, null, null, null, null ],
    [ null, null, null, null, null, null, null, null ],
    [ { type: 'pawn', color: 'white' }, { type: 'pawn', color: 'white' }, { type: 'pawn', color: 'white' }, { type: 'pawn', color: 'white' }, { type: 'pawn', color: 'white' }, { type: 'pawn', color: 'white' }, { type: 'pawn', color: 'white' }, { type: 'pawn', color: 'white' } ],
    [ { type: 'rook', color: 'white' }, { type: 'knight', color: 'white' }, { type: 'bishop', color: 'white' }, { type: 'queen', color: 'white' }, { type: 'king', color: 'white' }, { type: 'bishop', color: 'white' }, { type: 'knight', color: 'white' }, { type: 'rook', color: 'white' } ]
  ];
  constructor(private chessService: ChessService) {}

handleClick(row: number, col: number): void {
  const clickedCell = this.board[row][col];
  const coordinate = this.getCoordinate(row, col);

  if (this.selectedPosition) {
    const { row: fromRow, col: fromCol } = this.selectedPosition;
    this.board[row][col] = this.board[fromRow][fromCol];
    this.board[fromRow][fromCol] = null;
    this.selectedPosition = null;
  } else if (clickedCell) {
    this.selectedPosition = { row, col };

    console.log(coordinate)
    this.chessService.jogadasPossiveis({
      id: 1, 
      posicao: coordinate
    }).subscribe({
      next: (res) => {
        console.log('Jogadas possíveis:', res.msg); // exibe no console por enquanto
      },
      error: (err) => {
        console.error('Erro ao buscar jogadas possíveis:', err);
      }
    });
  }
}

  getCoordinate(row: number, col: number): string {
    const files = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'];
    const ranks = [8, 7, 6, 5, 4, 3, 2, 1];
    return ranks[row] + files[col];
  }

  ngOnInit(): void {
  }

}
