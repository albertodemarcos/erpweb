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

  constructor(private httpClient: HttpClient) {
    this.urlGeneral = 'http://localhost:8080';
    this.urlCrearArticulo = '/Articulos/crearArticulo';
    this.urlListadoArticulos = '/Articulos/listado.json';
  }

  // METODOS GENERALES

  public getArticulo(){
    console.log('METODO obtener');
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
