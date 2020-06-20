import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import { Cliente } from 'src/app/model/entitys/cliente.model';


@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private clienteAux: Cliente;
  private urlGeneral: string;
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
  private urlCrearCliente: string;
  private urlListadoClientes: string;

  constructor(private httpClient: HttpClient) {
    this.urlGeneral = 'http://localhost:8080'; //   http://192.168.1.39:8080
    this.urlCrearCliente = '/clientes/crearCliente';
    this.urlListadoClientes = '/clientes/listado.json';
  }

  // METODOS GENERALES
  public getCliente(){
    console.log('METODO obtener');
  }

  public crearCliente(cliente: Cliente): Observable<AccionRespuesta>{
    console.log('METODO crear');
    const urlPost = this.urlGeneral + this.urlCrearCliente;
    return this.httpClient.post<AccionRespuesta>(urlPost, cliente, {headers: this.httpHeaders});
  }

  public actualizarCliente(){
    console.log('METODO actualizar');
  }

  public eliminarCliente(){
    console.log('METODO GET');
  }

  public getClientes(): Promise<Cliente[]> {
    console.log('METODO listado');
    const urlPost = this.urlGeneral + this.urlListadoClientes;
    return this.httpClient.get<Cliente[]>(urlPost).pipe(
      map(response => response as Cliente[])
    ).toPromise();
  }

  /** METODO AUXILIARES */

  public saveClientePostFormulario(clienteP: Cliente): void {
    /* Guardamos el cliente recien creado para evitar
    *  volver a solicitar la informacion.
    */
    this.clienteAux = clienteP;
  }

  public getClienteAux(): Cliente {
    return this.clienteAux;
  }


}
