import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import { Compra } from 'src/app/model/entitys/compra.model';


@Injectable({
  providedIn: 'root'
})
export class CompraService {

  private urlGeneral: string;
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
  private urlCrearCompra: string;
  private urlListadoCompras: string;
  private urlGetCompra: string;

  constructor(private httpClient: HttpClient) {
    this.urlGeneral = 'http://localhost:8080';
    this.urlCrearCompra = '/compras/crearCompra';
    this.urlListadoCompras = '/compras/listado.json';
    this.urlGetCompra = '/compras/compra/';
  }

  // METODOS GENERALES

  public getCompra(id: number): Observable<AccionRespuesta> {
    console.log('METODO obtener');
    const urlGet = this.urlGeneral + this.urlGetCompra + id;
    return this.httpClient.get<AccionRespuesta>(urlGet);
  }

  public crearCompra(compra: Compra): Observable<AccionRespuesta>{
    console.log('METODO crear');
    const urlPost = this.urlGeneral + this.urlCrearCompra;
    return this.httpClient.post<AccionRespuesta>(urlPost, compra, {headers: this.httpHeaders});
  }

  public actualizarCompra(){
    console.log('METODO actualizar');
  }

  public eliminarCompra(){
    console.log('METODO GET');
  }

  public getCompras(): Promise<Compra[]> {
    console.log('METODO listado');
    const urlPost = this.urlGeneral + this.urlListadoCompras;
    return this.httpClient.get<Compra[]>(urlPost).pipe(map(response => response as Compra[])).toPromise();
  }

}
