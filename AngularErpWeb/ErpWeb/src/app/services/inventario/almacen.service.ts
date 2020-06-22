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
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
  private urlCrearAlmacen: string;
  private urlListadoAlmacenes: string;

  constructor(private httpClient: HttpClient) {
    this.urlGeneral = 'http://localhost:8080';
    this.urlCrearAlmacen = '/almacenes/crearAlmacen';
    this.urlListadoAlmacenes = '/almacenes/listado.json';
  }

  // METODOS GENERALES

  public getAlmacen(){
    console.log('METODO obtener');
  }

  public crearAlmacen(almacen: Almacen): Observable<AccionRespuesta>{
    console.log('METODO crear');
    const urlPost = this.urlGeneral + this.urlCrearAlmacen;
    return this.httpClient.post<AccionRespuesta>(urlPost, almacen, {headers: this.httpHeaders});
  }

  public actualizarAlmacen(){
    console.log('METODO actualizar');
  }

  public eliminarAlmacen(){
    console.log('METODO GET');
  }

  public getAlmacenes(): Promise<Almacen[]> {
    console.log('METODO listado');
    const urlPost = this.urlGeneral + this.urlListadoAlmacenes;
    return this.httpClient.get<Almacen[]>(urlPost).pipe(map(response => response as Almacen[])).toPromise();
  }
}
