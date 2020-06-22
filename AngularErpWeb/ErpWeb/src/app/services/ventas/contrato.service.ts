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
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
  private urlCrearContrato: string;
  private urlListadoContratos: string;

  constructor(private httpClient: HttpClient) {
    this.urlGeneral = 'http://localhost:8080';
    this.urlCrearContrato = '/contratos/crearContrato';
    this.urlListadoContratos = '/contratos/listado.json';
  }

  // METODOS GENERALES

  public getContrato(){
    console.log('METODO obtener');
  }

   public crearContrato(contrato: Contrato): Observable<AccionRespuesta>{
    console.log('METODO crear');
    const urlPost = this.urlGeneral + this.urlCrearContrato;
    return this.httpClient.post<AccionRespuesta>(urlPost, contrato, {headers: this.httpHeaders});
  }

  public actualizarContrato(){
    console.log('METODO actualizar');
  }

  public eliminarContrato(){
    console.log('METODO GET');
  }

  public getContratos(): Promise<Contrato[]> {
    console.log('METODO listado');
    const urlPost = this.urlGeneral + this.urlListadoContratos;
    return this.httpClient.get<Contrato[]>(urlPost).pipe(map(response => response as Contrato[])).toPromise();
  }
}
