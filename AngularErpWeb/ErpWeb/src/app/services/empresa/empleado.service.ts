import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import { Empleado } from 'src/app/model/entitys/empleado.model';


@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {

  private urlGeneral: string;
  private httpHeaders: HttpHeaders;
  private urlCrearEmpleado: string;
  private urlListadoEmpleados: string;
  private urlGetEmpleado: string;
  private urlEditarEmpleado: string;
  private urlEliminarEmpleado: string;

  constructor(private httpClient: HttpClient) {
    this.urlGeneral = 'http://localhost:8080';
    this.urlCrearEmpleado = '/empleados/crearEmpleado';
    this.urlListadoEmpleados = '/empleados/listado.json';
    this.urlGetEmpleado = '/empleados/empleado/';
    this.urlEditarEmpleado = '/empleados/editarEmpleado/';
    this.urlEliminarEmpleado = '/empleados/eliminarEmpleado/';
    this.httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8',
       Authorization: 'Bearer ' + sessionStorage.getItem('token'),
       Accept: '*/*',
    });
  }

  // METODOS GENERALES
  public getEmpleado(id: number): Observable<AccionRespuesta> {
    console.log('METODO obtener');
    const urlGet = this.urlGeneral + this.urlGetEmpleado + id;
    return this.httpClient.get<AccionRespuesta>(urlGet, {headers: this.httpHeaders});
  }

  public getEmpleadoEditar(empleadoId: number): Observable<AccionRespuesta>{
    console.log('METODO actualizar');
    const urlGet = this.urlGeneral + this.urlEditarEmpleado + empleadoId;
    return this.httpClient.get<AccionRespuesta>(urlGet, {headers: this.httpHeaders});
  }

  public crearEmpleado(empleado: Empleado): Observable<AccionRespuesta>{
    console.log('METODO crear');
    const urlPost = this.urlGeneral + this.urlCrearEmpleado;
    return this.httpClient.post<AccionRespuesta>(urlPost, empleado, {headers: this.httpHeaders});
  }

  public actualizarEmpleado(empleado: Empleado): Observable<AccionRespuesta>{
    console.log('METODO actualizar');
    const urlPost = this.urlGeneral + this.urlEditarEmpleado;
    return this.httpClient.post<AccionRespuesta>(urlPost, empleado, {headers: this.httpHeaders});
  }

  public eliminarEmpleado(empleadoId: number): Observable<AccionRespuesta>{
    console.log('METODO GET');
    const urlGet = this.urlGeneral + this.urlEliminarEmpleado + empleadoId;
    return this.httpClient.get<AccionRespuesta>(urlGet, {headers: this.httpHeaders});
  }

  public getEmpleados(): Promise<Empleado[]> {
    console.log('METODO listado');
    const urlPost = this.urlGeneral + this.urlListadoEmpleados;
    return this.httpClient.get<Empleado[]>(urlPost, {headers: this.httpHeaders}).pipe(map(response => response as Empleado[])).toPromise();
  }

}
