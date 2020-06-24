import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import { Empresa } from 'src/app/model/entitys/empresa.model';


@Injectable({
  providedIn: 'root'
})
export class EmpresaService {

  private urlGeneral: string;
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
  private urlCrearEmpresa: string;
  private urlGetEmpresa: string;

  constructor(private httpClient: HttpClient) {
    this.urlGeneral = 'http://localhost:8080';
    this.urlCrearEmpresa = '/empresas/crearEmpresa';
    this.urlGetEmpresa = '/empresas/empresa/';
  }

  // METODOS GENERALES
  public getEmpresa(id: number): Observable<AccionRespuesta> {
    console.log('METODO obtener');
    const urlGet = this.urlGeneral + this.urlGetEmpresa + id;
    return this.httpClient.get<AccionRespuesta>(urlGet);
  }

  public crearEmpresa(empresa: Empresa): Observable<AccionRespuesta> {
    console.log('METODO crear');
    const urlPost = this.urlGeneral + this.urlCrearEmpresa;
    return this.httpClient.post<AccionRespuesta>(urlPost, empresa, {headers: this.httpHeaders});
  }

  public actualizarEmpresa(){
    console.log('METODO actualizar');
  }

  public eliminarEmpresa(){
    console.log('METODO GET');
  }

}
