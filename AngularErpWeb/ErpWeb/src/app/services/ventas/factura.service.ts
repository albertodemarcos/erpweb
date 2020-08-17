import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import { Factura } from 'src/app/model/entitys/factura.model';

@Injectable({
  providedIn: 'root'
})
export class FacturaService {

  private urlGeneral: string;
  private httpHeaders: HttpHeaders;
  private urlCrearFactura: string;
  private urlListadoFacturas: string;
  private urlGetFactura: string;
  private urlEditarFactura: string;
private urlEliminarFactura: string;

  constructor(private httpClient: HttpClient) {
    this.urlGeneral = 'http://localhost:8080';
    this.urlCrearFactura = '/facturas/crearFactura';
    this.urlListadoFacturas = '/facturas/listado.json';
    this.urlGetFactura = '/facturas/factura/';
    this.urlEditarFactura = '/facturas/editarFactura/';
    this.urlEliminarFactura = '/facturas/eliminarFactura/';
    this.httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8',
       Authorization: 'Bearer ' + sessionStorage.getItem('token'),
       Accept: '*/*',
    });
  }

  // METODOS GENERALES
  public getFactura(id: number): Observable<AccionRespuesta> {
    console.log('METODO obtener');
    const urlGet = this.urlGeneral + this.urlGetFactura + id;
    return this.httpClient.get<AccionRespuesta>(urlGet, {headers: this.httpHeaders});
  }

  public getFacturaEditar(facturaId: number): Observable<AccionRespuesta>{
    console.log('METODO actualizar');
    const urlGet = this.urlGeneral + this.urlEditarFactura + facturaId;
    return this.httpClient.get<AccionRespuesta>(urlGet, {headers: this.httpHeaders});
  }

  public crearFactura(factura: Factura): Observable<AccionRespuesta>{
    console.log('METODO crear');
    const urlPost = this.urlGeneral + this.urlCrearFactura;
    return this.httpClient.post<AccionRespuesta>(urlPost, factura, {headers: this.httpHeaders});
  }

  public actualizarFactura(factura: Factura): Observable<AccionRespuesta>{
    console.log('METODO actualizar');
    const urlPost = this.urlGeneral + this.urlEditarFactura;
    return this.httpClient.post<AccionRespuesta>(urlPost, factura, {headers: this.httpHeaders});
  }

  public eliminarFactura(facturaId: number): Observable<AccionRespuesta>{
    console.log('METODO GET');
    const urlGet = this.urlGeneral + this.urlEliminarFactura + facturaId;
    return this.httpClient.get<AccionRespuesta>(urlGet, {headers: this.httpHeaders});
  }

  public getFacturas(): Promise<Factura[]> {
    console.log('METODO listado');
    const urlPost = this.urlGeneral + this.urlListadoFacturas;
    return this.httpClient.get<Factura[]>(urlPost, {headers: this.httpHeaders}).pipe(map(response => response as Factura[])).toPromise();
  }
}
