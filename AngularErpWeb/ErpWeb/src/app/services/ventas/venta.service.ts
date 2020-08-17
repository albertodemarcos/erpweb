import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import { Venta } from 'src/app/model/entitys/venta.model';

@Injectable({
  providedIn: 'root'
})
export class VentaService {

  private urlGeneral: string;
  private httpHeaders: HttpHeaders;
  private urlCrearVenta: string;
  private urlListadoVentas: string;
  private urlGetVenta: string;
  private urlEditarVenta: string;
  private urlEliminarVenta: string;

  constructor(private httpClient: HttpClient) {
    this.urlGeneral = 'http://localhost:8080';
    this.urlCrearVenta = '/ventas/crearVenta';
    this.urlListadoVentas = '/ventas/listado.json';
    this.urlGetVenta = '/ventas/venta/';
    this.urlEditarVenta = '/ventas/editarVenta/';
    this.urlEliminarVenta = '/ventas/eliminarVenta/';
    this.httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8',
       Authorization: 'Bearer ' + sessionStorage.getItem('token'),
       Accept: '*/*',
    });
  }

  // METODOS GENERALES
  public getVenta(id: number): Observable<AccionRespuesta> {
    console.log('METODO obtener');
    const urlGet = this.urlGeneral + this.urlGetVenta + id;
    return this.httpClient.get<AccionRespuesta>(urlGet);
  }

  public getVentaEditar(ventaId: number): Observable<AccionRespuesta>{
    console.log('METODO actualizar');
    const urlGet = this.urlGeneral + this.urlEditarVenta + ventaId;
    return this.httpClient.get<AccionRespuesta>(urlGet);
  }

  public crearVenta(venta: Venta): Observable<AccionRespuesta>{
    console.log('METODO crear');
    const urlPost = this.urlGeneral + this.urlCrearVenta;
    return this.httpClient.post<AccionRespuesta>(urlPost, venta, {headers: this.httpHeaders});
  }

  public actualizarVenta(venta: Venta): Observable<AccionRespuesta>{
    console.log('METODO actualizar');
    const urlPost = this.urlGeneral + this.urlEditarVenta;
    return this.httpClient.post<AccionRespuesta>(urlPost, venta, {headers: this.httpHeaders});
  }

  public eliminarVenta(ventaId: number): Observable<AccionRespuesta>{
    console.log('METODO GET');
    const urlGet = this.urlGeneral + this.urlEliminarVenta + ventaId;
    return this.httpClient.get<AccionRespuesta>(urlGet);
  }

  public getVentas(): Promise<Venta[]> {
    console.log('METODO listado');
    const urlPost = this.urlGeneral + this.urlListadoVentas;
    return this.httpClient.get<Venta[]>(urlPost).pipe(map(response => response as Venta[])).toPromise();
  }
}
