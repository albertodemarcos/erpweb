import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import { Almacen } from 'src/app/model/entitys/almacen.model';
import { Articulo } from 'src/app/model/entitys/articulo.model';

@Injectable({
  providedIn: 'root'
})
export class AutocompletarService {

  public paramatroExterno: string;

  private urlGeneral: string;
  private httpHeaders: HttpHeaders;
  private urlGetAlmacen: string;
  private urlGetArticulo: string;

  constructor(private httpClient: HttpClient) {
    this.urlGeneral = 'http://localhost:8080/autocompletar';
    this.urlGetAlmacen = '/almacenes/listado.json';
    this.urlGetArticulo = '/articulos/listado.json';
    this.httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8',
       Authorization: 'Bearer ' + sessionStorage.getItem('token'),
       Accept: '*/*',
    });
    this.paramatroExterno = '';
  }

  // Metodos Generales

  /**
   * Nos devuelve una lista de almacenes
   */
  getAlmacenAutocompletar(term: string): Promise<Almacen[]>{
    console.log('METODO listado');
    const urlGet = this.urlGeneral + this.urlGetAlmacen + '?term=' + term;
    return this.httpClient.get<Almacen[]>(urlGet, {headers: this.httpHeaders}).pipe(map(response => response as Almacen[])).toPromise();
  }

  /**
   * Nos devuelve una lista de articulos
   */
  getArticuloAutcompletar(term: string, completo?: boolean): Promise<Articulo[]>{
    console.log('METODO listado');
    const urlGet = this.urlGeneral + this.urlGetArticulo + '?term=' + term + '&&completo=' + completo;
    return this.httpClient.get<Articulo[]>(urlGet, {headers: this.httpHeaders}).pipe(map(response => response as Articulo[])).toPromise();
  }


}
