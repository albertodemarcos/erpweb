import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AccionRespuesta } from '../../model/utiles/accion-respuesta.model';
import { Usuario } from 'src/app/model/entitys/usuario.model';

@Injectable({
  providedIn: 'root'
})
export class UsuarioLoginService {

  private urlGeneral: string;
  private urlAutenticaUsuario: string;
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});

  constructor(private httpClient: HttpClient) {
    this.urlGeneral = 'http://localhost:8080'; //   http://192.168.1.39:8080
    this.urlAutenticaUsuario = '/inicio/login';
   }

  autenticarUsuario(usuario: Usuario): Observable<AccionRespuesta> {
    console.log('Entramos a autenticar al usuari con nombre: ' + ' y contraseña: ' );
    const urlPost = this.urlGeneral + this.urlAutenticaUsuario;
    return this.httpClient.post<AccionRespuesta>(urlPost, usuario, {headers: this.httpHeaders});
  }

  esUsuarioLogado() {
    const user = sessionStorage.getItem('username');
    console.log(!(user === null));
    return !(user === null);
  }

  salirApp(): string {
    sessionStorage.removeItem('username');
    return '';
  }


}
