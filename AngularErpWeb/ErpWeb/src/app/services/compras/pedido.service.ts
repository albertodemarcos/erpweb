import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import { Pedido } from 'src/app/model/entitys/pedido.model';

@Injectable({
  providedIn: 'root'
})
export class PedidoService {

  private urlGeneral: string;
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
  private urlCrearPedido: string;
  private urlListadoPedidos: string;

  constructor(private httpClient: HttpClient) {
    this.urlGeneral = 'http://localhost:8080';
    this.urlCrearPedido = '/clientes/crearCliente';
    this.urlListadoPedidos = '/clientes/listado.json';
  }

  // METODOS GENERALES

  public getPedido(){
    console.log('METODO obtener');
   }

   public crearPedido(pedido: Pedido): Observable<AccionRespuesta> {
    console.log('METODO crear');
    const urlPost = this.urlGeneral + this.urlCrearPedido;
    return this.httpClient.post<AccionRespuesta>(urlPost, pedido, {headers: this.httpHeaders});
   }

   public actualizarPedido(){
    console.log('METODO actualizar');
   }

   public eliminarPedido(){
    console.log('METODO GET');
   }

   public getPedidos(): Promise<Pedido[]> {
    console.log('METODO listado');
    const urlPost = this.urlGeneral + this.urlListadoPedidos;
    return this.httpClient.get<Pedido[]>(urlPost).pipe(map(response => response as Pedido[])).toPromise();
  }
}
