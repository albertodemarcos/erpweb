import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import { Cliente } from 'src/app/model/entitys/cliente.model';


@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private urlGeneral: string;
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
  private urlCrearCliente: string;

  constructor(private httpClient: HttpClient) {
    this.urlGeneral = 'http://localhost:8080'; //   http://192.168.1.39:8080
    this.urlCrearCliente = '/clientes/crearCliente';
  }

  // METODOS GENERALES
  public getCliente(){
    console.log('METODO obtener');
   }

   public crearCliente(cliente: Cliente): Observable<AccionRespuesta>{

    console.log('METODO crear');

    let accionRespuesta = new AccionRespuesta();

    let mapa = new Map<string, object>();

    mapa.set('clienteDto', cliente);

    accionRespuesta = {
      id: 0,
      codigo: '',
      respuesta: '',
      resultado: true,
      data: mapa,
    };
    // `${this.urlGeneral}${this.urlCrearCliente}`
    return this.httpClient.post<AccionRespuesta>('http://localhost:8080/clientes/crearCliente',accionRespuesta, {headers: this.httpHeaders});
   }

   public actualizarCliente(){
    console.log('METODO actualizar');
   }

   public eliminarCliente(){
    console.log('METODO GET');
   }
}
