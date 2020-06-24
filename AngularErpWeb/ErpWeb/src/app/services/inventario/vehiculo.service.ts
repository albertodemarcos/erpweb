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
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
  private urlCrearVehiculo: string;
  private urlListadoVehiculos: string;
  private urlGetVehiculo: string;

  constructor(private httpClient: HttpClient) {
    this.urlGeneral = 'http://localhost:8080';
    this.urlCrearVehiculo = '/vehiculos/crearVehiculo';
    this.urlListadoVehiculos = '/vehiculos/listado.json';
    this.urlGetVehiculo = '/vehiculos/vehiculo/';
  }

  // METODOS GENERALES
  public getVehiculo(id: number): Observable<AccionRespuesta> {
    console.log('METODO obtener');
    const urlGet = this.urlGeneral + this.urlGetVehiculo + id;
    return this.httpClient.get<AccionRespuesta>(urlGet);
  }

  public crearVehiculo(vehiculo: Vehiculo): Observable<AccionRespuesta>{
    console.log('METODO crear');
    const urlPost = this.urlGeneral + this.urlCrearVehiculo;
    return this.httpClient.post<AccionRespuesta>(urlPost, vehiculo, {headers: this.httpHeaders});
  }

  public actualizarVehiculo(){
    console.log('METODO actualizar');
  }

  public eliminarVehiculo(){
    console.log('METODO GET');
  }

  public getVehiculos(): Promise<Vehiculo[]> {
    console.log('METODO listado');
    const urlPost = this.urlGeneral + this.urlListadoVehiculos;
    return this.httpClient.get<Vehiculo[]>(urlPost).pipe(map(response => response as Vehiculo[])).toPromise();
  }

}
