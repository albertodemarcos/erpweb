import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import { Contrato } from 'src/app/model/entitys/contrato.model';

@Injectable({
  providedIn: 'root'
})
export class ContratoService {

  private urlGeneral: string;
  private httpHeaders: HttpHeaders;
  private urlCrearContrato: string;
  private urlListadoContratos: string;
  private urlGetContrato: string;
  private urlEditarContrato: string;
  private urlEliminarContrato: string;
  private urlCantidadArticuloContrato: string;

  constructor(private httpClient: HttpClient) {
    this.urlGeneral = 'http://localhost:8080';
    this.urlCrearContrato = '/contratos/crearContrato';
    this.urlListadoContratos = '/contratos/listado.json';
    this.urlGetContrato = '/contratos/contrato/';
    this.urlEditarContrato = '/contratos/editarContrato/';
    this.urlEliminarContrato = '/contratos/eliminarContrato/';
    this.urlCantidadArticuloContrato = '/contratos/cantidadArticulos';
    this.httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8',
       Authorization: 'Bearer ' + sessionStorage.getItem('token'),
       Accept: '*/*',
    });
  }

  // METODOS GENERALES
  public getContrato(id: number): Observable<AccionRespuesta> {
    console.log('METODO obtener');
    const urlGet = this.urlGeneral + this.urlGetContrato + id;
    return this.httpClient.get<AccionRespuesta>(urlGet, {headers: this.httpHeaders});
  }

  public getContratoEditar(contratoId: number): Observable<AccionRespuesta>{
    console.log('METODO actualizar');
    const urlGet = this.urlGeneral + this.urlEditarContrato + contratoId;
    return this.httpClient.get<AccionRespuesta>(urlGet, {headers: this.httpHeaders});
  }

  public crearContrato(contrato: Contrato): Observable<AccionRespuesta>{
    console.log('METODO crear');
    const urlPost = this.urlGeneral + this.urlCrearContrato;
    return this.httpClient.post<AccionRespuesta>(urlPost, contrato, {headers: this.httpHeaders});
  }

  public actualizarContrato(contrato: Contrato): Observable<AccionRespuesta>{
    console.log('METODO actualizar');
    const urlPost = this.urlGeneral + this.urlEditarContrato;
    return this.httpClient.post<AccionRespuesta>(urlPost, contrato, {headers: this.httpHeaders});
  }

  public eliminarContrato(contratoId: number): Observable<AccionRespuesta>{
    console.log('METODO GET');
    const urlGet = this.urlGeneral + this.urlEliminarContrato + contratoId;
    return this.httpClient.get<AccionRespuesta>(urlGet, {headers: this.httpHeaders});
  }

  public getContratos(): Promise<Contrato[]> {
    console.log('METODO listado');
    const urlPost = this.urlGeneral + this.urlListadoContratos;
    return this.httpClient.get<Contrato[]>(urlPost, {headers: this.httpHeaders}).pipe(map(response => response as Contrato[])).toPromise();
  }

  /*public comprobarCantidadArticulos(id: number, articulosCantidad: {}): Promise<AccionRespuesta> {
    console.log('METODO GET artiuclo');
    // tslint:disable-next-line: prefer-const
    let contrato: Contrato = new Contrato();
    contrato.articulosCantidad = articulosCantidad;
    contrato.id = id;
    const urlPost = this.urlGeneral + this.urlCantidadArticuloContrato ;
    return this.httpClient.post<AccionRespuesta>(urlPost, contrato, {headers: this.httpHeaders}).pipe(map(response => response as AccionRespuesta)).toPromise();
  }*/
}
