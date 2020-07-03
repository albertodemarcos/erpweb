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
  private urlGetPedido: string;
  private urlEditarPedido: string;
  private urlEliminarPedido: string;

  constructor(private httpClient: HttpClient) {
    this.urlGeneral = 'http://localhost:8080';
    this.urlCrearPedido = '/pedidos/crearPedido';
    this.urlListadoPedidos = '/pedidos/listado.json';
    this.urlGetPedido = '/pedidos/pedido/';
    this.urlEditarPedido = '/pedidos/editarPedido/';
    this.urlEliminarPedido = '/pedidos/eliminarPedido/';
  }

  // METODOS GENERALES
  public getPedido(id: number): Observable<AccionRespuesta> {
    console.log('METODO obtener');
    const urlGet = this.urlGeneral + this.urlGetPedido + id;
    return this.httpClient.get<AccionRespuesta>(urlGet);
  }

  public getPedidoEditar(pedidoId: number): Observable<AccionRespuesta>{
    console.log('METODO actualizar');
    const urlGet = this.urlGeneral + this.urlEditarPedido + pedidoId;
    return this.httpClient.get<AccionRespuesta>(urlGet);
  }

  public crearPedido(pedido: Pedido): Observable<AccionRespuesta> {
    console.log('METODO crear');
    const urlPost = this.urlGeneral + this.urlCrearPedido;
    return this.httpClient.post<AccionRespuesta>(urlPost, pedido, {headers: this.httpHeaders});
  }

  public actualizarPedido(pedido: Pedido): Observable<AccionRespuesta>{
    console.log('METODO actualizar');
    const urlPost = this.urlGeneral + this.urlEditarPedido;
    return this.httpClient.post<AccionRespuesta>(urlPost, pedido, {headers: this.httpHeaders});
  }

  public eliminarPedido(pedidoId: number): Observable<AccionRespuesta>{
    console.log('METODO GET');
    const urlGet = this.urlGeneral + this.urlEliminarPedido + pedidoId;
    return this.httpClient.get<AccionRespuesta>(urlGet);
  }

  public getPedidos(): Promise<Pedido[]> {
    console.log('METODO listado');
    const urlPost = this.urlGeneral + this.urlListadoPedidos;
    return this.httpClient.get<Pedido[]>(urlPost).pipe(map(response => response as Pedido[])).toPromise();
  }

}
