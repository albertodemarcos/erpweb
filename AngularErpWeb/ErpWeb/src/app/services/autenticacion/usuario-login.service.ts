import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { map } from 'rxjs/operators';
import { AccionRespuesta } from '../../model/utiles/accion-respuesta.model';
import { Usuario } from 'src/app/model/entitys/usuario.model';
import { AutenticacionRequest } from 'src/app/model/entitys/autenticacion-request.model';



@Injectable({
  providedIn: 'root'
})
export class UsuarioLoginService {

  private usuarioLogado = new Subject<boolean>();
  private urlGeneral: string;
  private urlAutenticaUsuario: string;
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});

  constructor(private httpClient: HttpClient) {
    this.urlGeneral = 'http://localhost:8080'; //   http://192.168.1.39:8080
    this.urlAutenticaUsuario = '/inicio/login';
   }

  public autenticarUsuario(usuario: AutenticacionRequest): Observable<AccionRespuesta> {
    console.log('Entramos a autenticar al usuari con nombre: ' + ' y contraseña: ' );
    const urlPost = this.urlGeneral + this.urlAutenticaUsuario;
    return this.httpClient.post<AccionRespuesta>(urlPost, usuario, {headers: this.httpHeaders});
  }

  public esUsuarioLogado() {
    const user = sessionStorage.getItem('username');
    console.log(!(user === null));
    return !(user === null);
  }

  public esAdministrador(): boolean{

    return false;
  }

  salirApp(): string {
    sessionStorage.removeItem('username');
    return '';
  }


}
