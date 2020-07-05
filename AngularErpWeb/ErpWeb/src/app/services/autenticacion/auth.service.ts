import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AccionRespuesta } from '../../model/utiles/accion-respuesta.model';
import { Usuario } from 'src/app/model/entitys/usuario.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private urlGeneral: string;
  private urlPostLoginUser: string;
  private credenciales: string;
  private httpHeaders: HttpHeaders;
  private urlParams: URLSearchParams;

  constructor(private httpClient: HttpClient) {
    this.urlGeneral = 'http://localhost:8080'; //   http://192.168.1.39:8080
    this.urlPostLoginUser = '/oauth/token';
    this.credenciales = btoa('angularapp' + ':' + '12345');
    this.httpHeaders = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
      // tslint:disable-next-line: object-literal-key-quotes
      'Authorization': 'Basic' + this.credenciales
    });

  }


  login(usuario: Usuario): Observable<AccionRespuesta>{
    console.log('METODO logear');
    const urlPost = this.urlGeneral + this.urlPostLoginUser;
    this.rellenarUrlSearchParams(usuario);
    return this.httpClient.post<AccionRespuesta>(urlPost, this.urlParams.toString(), {headers: this.httpHeaders});
  }


  /*METODOS AUXILIARES */

  rellenarUrlSearchParams(usuario: Usuario): void{
    this.urlParams = new URLSearchParams();
    this.urlParams.set('grant_type', 'password');
    this.urlParams.set('username', usuario.username);
    this.urlParams.set('password', usuario.password);
  }

}
