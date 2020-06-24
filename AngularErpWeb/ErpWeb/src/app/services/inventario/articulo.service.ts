import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import { Articulo } from 'src/app/model/entitys/articulo.model';

@Injectable({
  providedIn: 'root'
})
export class ArticuloService {

  private urlGeneral: string;
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
  private urlCrearArticulo: string;
  private urlListadoArticulos: string;
  private urlGetArticulo: string;

  constructor(private httpClient: HttpClient) {
    this.urlGeneral = 'http://localhost:8080';
    this.urlCrearArticulo = '/articulos/crearArticulo';
    this.urlListadoArticulos = '/articulos/listado.json';
    this.urlGetArticulo = '/articulos/articulo/';
  }

  // METODOS GENERALES
  public getArticulo(id: number): Observable<AccionRespuesta> {
    console.log('METODO obtener');
    const urlGet = this.urlGeneral + this.urlGetArticulo + id;
    return this.httpClient.get<AccionRespuesta>(urlGet);
  }

  public crearArticulo(articulo: Articulo): Observable<AccionRespuesta>{
    console.log('METODO crear');
    const urlPost = this.urlGeneral + this.urlCrearArticulo;
    return this.httpClient.post<AccionRespuesta>(urlPost, articulo, {headers: this.httpHeaders});
  }

  public actualizarArticulo(){
    console.log('METODO actualizar');
  }

  public eliminarArticulo(){
    console.log('METODO GET');
  }

  public getArticulos(): Promise<Articulo[]> {
    console.log('METODO listado');
    const urlPost = this.urlGeneral + this.urlListadoArticulos;
    return this.httpClient.get<Articulo[]>(urlPost).pipe(map(response => response as Articulo[])).toPromise();
  }

}
