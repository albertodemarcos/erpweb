import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import { Vehiculo } from 'src/app/model/entitys/vehiculo.model';

@Injectable({
  providedIn: 'root'
})
export class VehiculoService {

  private urlGeneral: string;
  private httpHeaders: HttpHeaders;
  private urlCrearVehiculo: string;
  private urlListadoVehiculos: string;
  private urlGetVehiculo: string;
  private urlEditarVehiculo: string;
  private urlEliminarVehiculo: string;

  constructor(private httpClient: HttpClient) {
    this.urlGeneral = 'http://localhost:8080';
    this.urlCrearVehiculo = '/vehiculos/crearVehiculo';
    this.urlListadoVehiculos = '/vehiculos/listado.json';
    this.urlGetVehiculo = '/vehiculos/vehiculo/';
    this.urlEditarVehiculo = '/vehiculos/editarVehiculo/';
    this.urlEliminarVehiculo = '/vehiculos/eliminarVehiculo/';
    this.httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8',
       Authorization: 'Bearer ' + sessionStorage.getItem('token'),
       Accept: '*/*',
    });
  }

  // METODOS GENERALES
  public getVehiculo(id: number): Observable<AccionRespuesta> {
    console.log('METODO obtener');
    const urlGet = this.urlGeneral + this.urlGetVehiculo + id;
    return this.httpClient.get<AccionRespuesta>(urlGet);
  }

  public getVehiculoEditar(vehiculoId: number): Observable<AccionRespuesta>{
    console.log('METODO actualizar');
    const urlGet = this.urlGeneral + this.urlEditarVehiculo + vehiculoId;
    return this.httpClient.get<AccionRespuesta>(urlGet);
  }

  public crearVehiculo(vehiculo: Vehiculo): Observable<AccionRespuesta>{
    console.log('METODO crear');
    const urlPost = this.urlGeneral + this.urlCrearVehiculo;
    return this.httpClient.post<AccionRespuesta>(urlPost, vehiculo, {headers: this.httpHeaders});
  }

  public actualizarVehiculo(vehiculo: Vehiculo): Observable<AccionRespuesta>{
    console.log('METODO actualizar');
    const urlPost = this.urlGeneral + this.urlEditarVehiculo;
    return this.httpClient.post<AccionRespuesta>(urlPost, vehiculo, {headers: this.httpHeaders});
  }

  public eliminarVehiculo(vehiculoId: number): Observable<AccionRespuesta>{
    console.log('METODO GET');
    const urlGet = this.urlGeneral + this.urlEliminarVehiculo + vehiculoId;
    return this.httpClient.get<AccionRespuesta>(urlGet);
  }

  public getVehiculos(): Promise<Vehiculo[]> {
    console.log('METODO listado');
    const urlPost = this.urlGeneral + this.urlListadoVehiculos;
    return this.httpClient.get<Vehiculo[]>(urlPost).pipe(map(response => response as Vehiculo[])).toPromise();
  }

}
