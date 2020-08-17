import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import { Evento } from 'src/app/model/entitys/evento.model';


@Injectable({
  providedIn: 'root'
})
export class EventoService {

  private urlGeneral: string;
  private httpHeaders: HttpHeaders;
  private urlCrearEvento: string;
  private urlListadoEventos: string;
  private urlGetEvento: string;
  private urlEditarEvento: string;
  private urlEliminarEvento: string;

  constructor(private httpClient: HttpClient) {
    this.urlGeneral = 'http://localhost:8080';
    this.urlCrearEvento = '/eventos/crearEvento';
    this.urlListadoEventos = '/eventos/listado.json';
    this.urlGetEvento = '/eventos/evento/';
    this.urlEditarEvento = '/eventos/editarEvento/';
    this.urlEliminarEvento = '/eventos/eliminarEvento/';
    this.httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8',
       Authorization: 'Bearer ' + sessionStorage.getItem('token'),
       Accept: '*/*',
    });
  }

  // METODOS GENERALES

  public getEvento(id: number): Observable<AccionRespuesta>{
    console.log('METODO obtener');
    const urlGet = this.urlGeneral + this.urlGetEvento + id;
    return this.httpClient.get<AccionRespuesta>(urlGet, {headers: this.httpHeaders});
  }

  public getEventoEditar(eventoId: number): Observable<AccionRespuesta>{
    console.log('METODO actualizar');
    const urlGet = this.urlGeneral + this.urlEditarEvento + eventoId;
    return this.httpClient.get<AccionRespuesta>(urlGet, {headers: this.httpHeaders});
  }

  public crearEvento(evento: Evento): Observable<AccionRespuesta>{
    console.log('METODO crear');
    const urlPost = this.urlGeneral + this.urlCrearEvento;
    return this.httpClient.post<AccionRespuesta>(urlPost, evento, {headers: this.httpHeaders});
  }

  public actualizarEvento(evento: Evento): Observable<AccionRespuesta>{
    console.log('METODO actualizar');
    const urlPost = this.urlGeneral + this.urlEditarEvento;
    return this.httpClient.post<AccionRespuesta>(urlPost, evento, {headers: this.httpHeaders});
  }

  public eliminarEvento(eventoId: number): Observable<AccionRespuesta>{
    console.log('METODO GET');
    const urlGet = this.urlGeneral + this.urlEliminarEvento + eventoId;
    return this.httpClient.get<AccionRespuesta>(urlGet, {headers: this.httpHeaders});
  }

  public getEventos(): Promise<Evento[]> {
    console.log('METODO listado');
    console.log('Token: '  + sessionStorage.getItem('token'));
    const urlGet = this.urlGeneral + this.urlListadoEventos;
    return this.httpClient.get<Evento[]>(urlGet, {headers: this.httpHeaders}).pipe(map(response => response as Evento[])).toPromise();
  }

}
