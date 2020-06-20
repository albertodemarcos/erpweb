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
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
  private urlCrearEmpleado: string;
  private urlListadoEmpleados: string;

  constructor(private httpClient: HttpClient) {
    this.urlGeneral = 'http://localhost:8080';
    this.urlCrearEmpleado = '/clientes/crearCliente';
    this.urlListadoEmpleados = '/clientes/listado.json';
  }

  // METODOS GENERALES

  public getEmpleado(){
    console.log('METODO obtener');
   }

   public crearEmpleado(empleado: Empleado): Observable<AccionRespuesta>{
    console.log('METODO crear');
    const urlPost = this.urlGeneral + this.urlCrearEmpleado;
    return this.httpClient.post<AccionRespuesta>(urlPost, empleado, {headers: this.httpHeaders});
   }

   public actualizarEmpleado(){
    console.log('METODO actualizar');
   }

  public eliminarEmpleado(){
    console.log('METODO GET');
  }

  public getEmpleados(): Promise<Empleado[]> {
    console.log('METODO listado');
    const urlPost = this.urlGeneral + this.urlListadoEmpleados;
    return this.httpClient.get<Empleado[]>(urlPost).pipe(map(response => response as Empleado[])).toPromise();
  }

}
