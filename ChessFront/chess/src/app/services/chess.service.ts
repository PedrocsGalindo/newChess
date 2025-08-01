import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface CriarPartidaRequest {
  id: number;
}

export interface JogadaRequest {
  id: number;
  posicao: string;
  novaPosicao: string;
}

export interface JogadaPromocaoRequest {
  id: number;
  posicao: string;
  novaPosicao: string;
  novaPeca: string; 
}

export interface VerificarEstadoRequest {
  id: number;
  cor: string; 
}

export interface JogadasPossiveisRequest {
  id: number;
  posicao: string;
}

export interface ResponseApi {
  msg: string | string[];
}

@Injectable({
  providedIn: 'root'
})
export class ChessService {
  private baseUrl = 'http://localhost:8080/ChessGame';

  constructor(private http: HttpClient) {}

  criarPartida(data: CriarPartidaRequest): Observable<ResponseApi> {
    return this.http.post<ResponseApi>(`${this.baseUrl}/criarPartida`, data);
  }

  ultimaJogada(data: CriarPartidaRequest): Observable<ResponseApi> {
    return this.http.get<ResponseApi>(`${this.baseUrl}/ultimaJogada?id=${data.id}`);
  }


  moverPeca(data: JogadaRequest): Observable<ResponseApi> {
    return this.http.post<ResponseApi>(`${this.baseUrl}/moverPeca`, data);
  }

  moverPecaPromocao(data: JogadaPromocaoRequest): Observable<ResponseApi> {
    return this.http.post<ResponseApi>(`${this.baseUrl}/moverPecaPromocao`, data);
  }

  verificarEstado(data: VerificarEstadoRequest): Observable<ResponseApi> {
    const params = new URLSearchParams({
      id: data.id.toString(),
      cor: data.cor
    });

  return this.http.get<ResponseApi>(`${this.baseUrl}/verificarEstado?${params.toString()}`);
}

  jogadasPossiveis(data: JogadasPossiveisRequest): Observable<ResponseApi> {
    return this.http.post<ResponseApi>(`${this.baseUrl}/jogadasPossiveis`, data);
  }

  jogadaBot(data: VerificarEstadoRequest): Observable<ResponseApi> {
    return this.http.post<ResponseApi>(`${this.baseUrl}/jogadaBot`, data);
  }
}
