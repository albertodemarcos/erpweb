import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import { Evento } from 'src/app/model/entitys/evento.model';


@Injectable({
  providedIn: 'root'
})
export class EventoService {

  private urlGeneral: string;
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
  private urlCrearEvento: string;
  private urlListadoEventos: string;

  constructor(private httpClient: HttpClient) {
    this.urlGeneral = 'http://localhost:8080';
    this.urlCrearEvento = '/eventos/crearEvento';
    this.urlListadoEventos = '/eventos/listado.json';
  }

  // METODOS GENERALES

  public getEvento(){
    console.log('METODO obtener');
  }

  public crearEvento(evento: Evento): Observable<AccionRespuesta>{
    console.log('METODO crear');
    const urlPost = this.urlGeneral + this.urlCrearEvento;
    return this.httpClient.post<AccionRespuesta>(urlPost, evento, {headers: this.httpHeaders});
  }

  public actualizarEvento(){
    console.log('METODO actualizar');
  }

  public eliminarEvento(){
    console.log('METODO GET');
  }

  public getEventos(): Promise<Evento[]> {
    console.log('METODO listado');
    const urlPost = this.urlGeneral + this.urlListadoEventos;
    return this.httpClient.get<Evento[]>(urlPost).pipe(map(response => response as Evento[])).toPromise();
  }

}
