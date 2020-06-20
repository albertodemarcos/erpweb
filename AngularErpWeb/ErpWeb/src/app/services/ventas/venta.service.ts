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
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
  private urlCrearVenta: string;
  private urlListadoVentas: string;

  constructor(private httpClient: HttpClient) {
    this.urlGeneral = 'http://localhost:8080';
    this.urlCrearVenta = '/Ventas/crearVenta';
    this.urlListadoVentas = '/Ventas/listado.json';
  }

  // METODOS GENERALES

  public getVenta(){
    console.log('METODO obtener');
  }

  public crearVenta(venta: Venta): Observable<AccionRespuesta>{
    console.log('METODO crear');
    const urlPost = this.urlGeneral + this.urlCrearVenta;
    return this.httpClient.post<AccionRespuesta>(urlPost, venta, {headers: this.httpHeaders});
  }

  public actualizarVenta(){
    console.log('METODO actualizar');
  }

  public eliminarVenta(){
    console.log('METODO GET');
  }

  public getVentas(): Promise<Venta[]> {
    console.log('METODO listado');
    const urlPost = this.urlGeneral + this.urlListadoVentas;
    return this.httpClient.get<Venta[]>(urlPost).pipe(map(response => response as Venta[])).toPromise();
  }
}
