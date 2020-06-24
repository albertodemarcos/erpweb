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
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
  private urlCrearFactura: string;
  private urlListadoFacturas: string;
  private urlGetFactura: string;

  constructor(private httpClient: HttpClient) {
    this.urlGeneral = 'http://localhost:8080';
    this.urlCrearFactura = '/facturas/crearFactura';
    this.urlListadoFacturas = '/facturas/listado.json';
    this.urlGetFactura = '/facturas/factura/';
  }

  // METODOS GENERALES
  public getFactura(id: number): Observable<AccionRespuesta> {
    console.log('METODO obtener');
    const urlGet = this.urlGeneral + this.urlGetFactura + id;
    return this.httpClient.get<AccionRespuesta>(urlGet);
  }

  public crearFactura(factura: Factura): Observable<AccionRespuesta>{
    console.log('METODO crear');
    const urlPost = this.urlGeneral + this.urlCrearFactura;
    return this.httpClient.post<AccionRespuesta>(urlPost, factura, {headers: this.httpHeaders});
  }

  public actualizarFactura(){
    console.log('METODO actualizar');
  }

  public eliminarFactura(){
    console.log('METODO GET');
  }

  public getFacturas(): Promise<Factura[]> {
    console.log('METODO listado');
    const urlPost = this.urlGeneral + this.urlListadoFacturas;
    return this.httpClient.get<Factura[]>(urlPost).pipe(map(response => response as Factura[])).toPromise();
  }
}
