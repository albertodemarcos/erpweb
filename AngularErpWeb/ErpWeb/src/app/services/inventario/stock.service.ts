import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Stock } from 'src/app/model/entitys/stock.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';

@Injectable({
  providedIn: 'root'
})
export class StockService {

  private urlGeneral: string;
  private httpHeaders: HttpHeaders;
  private urlCrearStockAlmacen: string;
  private urlListadoStockAlmacenes: string;
  private urlGetStockAlmacen: string;
  private urlEditarStockAlmacen: string;
  private urlEliminarStockAlmacen: string;

  constructor(private httpClient: HttpClient) {
    this.urlGeneral = 'http://localhost:8080';
    this.urlCrearStockAlmacen = '/stock/crearStockArticulo';
    this.urlListadoStockAlmacenes = '/stock/listado.json';
    this.urlGetStockAlmacen = '/stock/stockArticulo/';
    this.urlEditarStockAlmacen = '/stock/editarStockArticulo/';
    this.urlEliminarStockAlmacen = '/stock/eliminarStockArticulo/';
    this.httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8',
       Authorization: 'Bearer ' + sessionStorage.getItem('token'),
       Accept: '*/*',
    });
  }

  // METODOS GENERALES

  public getStockAlmacen(id: number): Observable<AccionRespuesta> {
    console.log('METODO obtener');
    const urlGet = this.urlGeneral + this.urlGetStockAlmacen + id;
    return this.httpClient.get<AccionRespuesta>(urlGet, {headers: this.httpHeaders});
  }

  public getStockAlmacenEditar(stockAlmacenId: number): Observable<AccionRespuesta>{
    console.log('METODO actualizar');
    const urlGet = this.urlGeneral + this.urlEditarStockAlmacen + stockAlmacenId;
    return this.httpClient.get<AccionRespuesta>(urlGet, {headers: this.httpHeaders});
  }

  public crearStockAlmacen(stockAlmacen: Stock): Observable<AccionRespuesta>{
    console.log('METODO crear');
    const urlPost = this.urlGeneral + this.urlCrearStockAlmacen;
    return this.httpClient.post<AccionRespuesta>(urlPost, stockAlmacen, {headers: this.httpHeaders});
  }

  public actualizarStockAlmacen(stockAlmacen: Stock): Observable<AccionRespuesta>{
    console.log('METODO actualizar');
    const urlPost = this.urlGeneral + this.urlEditarStockAlmacen;
    return this.httpClient.post<AccionRespuesta>(urlPost, stockAlmacen, {headers: this.httpHeaders});
  }

  public eliminarStockAlmacen(stockAlmacenId: number): Observable<AccionRespuesta>{
    console.log('METODO GET');
    const urlGet = this.urlGeneral + this.urlEliminarStockAlmacen + stockAlmacenId;
    return this.httpClient.get<AccionRespuesta>(urlGet, {headers: this.httpHeaders});
  }

  public getStockAlmacenes(): Promise<Stock[]> {
    console.log('METODO listado');
    const urlPost = this.urlGeneral + this.urlListadoStockAlmacenes;
    return this.httpClient.get<Stock[]>(urlPost, {headers: this.httpHeaders}).pipe(map(response => response as Stock[])).toPromise();
  }

}
