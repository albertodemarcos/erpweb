import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import { Almacen } from 'src/app/model/entitys/almacen.model';

@Injectable({
  providedIn: 'root'
})
export class AlmacenService {

  private urlGeneral: string;
  private httpHeaders: HttpHeaders;
  private urlCrearAlmacen: string;
  private urlListadoAlmacenes: string;
  private urlGetAlmacen: string;
  private urlEditarAlmacen: string;
  private urlEliminarAlmacen: string;

  constructor(private httpClient: HttpClient) {
    this.urlGeneral = 'http://localhost:8080';
    this.urlCrearAlmacen = '/almacenes/crearAlmacen';
    this.urlListadoAlmacenes = '/almacenes/listado.json';
    this.urlGetAlmacen = '/almacenes/almacen/';
    this.urlEditarAlmacen = '/almacenes/editarAlmacen/';
    this.urlEliminarAlmacen = '/almacenes/eliminarAlmacen/';
    this.httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8',
       Authorization: 'Bearer ' + sessionStorage.getItem('token'),
       Accept: '*/*',
    });
  }

  // METODOS GENERALES
  public getAlmacen(id: number): Observable<AccionRespuesta> {
    console.log('METODO obtener');
    const urlGet = this.urlGeneral + this.urlGetAlmacen + id;
    return this.httpClient.get<AccionRespuesta>(urlGet, {headers: this.httpHeaders});
  }

  public getAlmacenEditar(almacenId: number): Observable<AccionRespuesta>{
    console.log('METODO actualizar');
    const urlGet = this.urlGeneral + this.urlEditarAlmacen + almacenId;
    return this.httpClient.get<AccionRespuesta>(urlGet, {headers: this.httpHeaders});
}

  public crearAlmacen(almacen: Almacen): Observable<AccionRespuesta>{
    console.log('METODO crear');
    const urlPost = this.urlGeneral + this.urlCrearAlmacen;
    return this.httpClient.post<AccionRespuesta>(urlPost, almacen, {headers: this.httpHeaders});
  }

  public actualizarAlmacen(almacen: Almacen): Observable<AccionRespuesta>{
    console.log('METODO actualizar');
    const urlPost = this.urlGeneral + this.urlEditarAlmacen;
    return this.httpClient.post<AccionRespuesta>(urlPost, almacen, {headers: this.httpHeaders});
  }

  public eliminarAlmacen(almacenId: number): Observable<AccionRespuesta>{
    console.log('METODO GET');
    const urlGet = this.urlGeneral + this.urlEliminarAlmacen + almacenId;
    return this.httpClient.get<AccionRespuesta>(urlGet, {headers: this.httpHeaders});
  }

  public getAlmacenes(): Promise<Almacen[]> {
    console.log('METODO listado');
    const urlPost = this.urlGeneral + this.urlListadoAlmacenes;
    return this.httpClient.get<Almacen[]>(urlPost, {headers: this.httpHeaders}).pipe(map(response => response as Almacen[])).toPromise();
  }
}
