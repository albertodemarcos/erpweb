import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import { Usuario } from 'src/app/model/entitys/usuario.model';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private urlGeneral: string;
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
  private urlCrearUsuario: string;
  private urlListadoUsuarios: string;
  private urlGetUsuario: string;
  private urlEditarUsuario: string;
  private urlEliminarUsuario: string;

  constructor(private httpClient: HttpClient) {
    this.urlGeneral = 'http://localhost:8080';
    this.urlCrearUsuario = '/usuarios/crearUsuario';
    this.urlListadoUsuarios = '/usuarios/listado.json';
    this.urlGetUsuario = '/usuarios/usuario/';
    this.urlEditarUsuario = '/usuarios/editarUsuario/';
    this.urlEliminarUsuario = '/usuarios/eliminarUsuario/';
  }

  // METODOS GENERALES
  public getUsuario(id: number): Observable<AccionRespuesta> {
    console.log('METODO obtener');
    const urlGet = this.urlGeneral + this.urlGetUsuario + id;
    return this.httpClient.get<AccionRespuesta>(urlGet);
  }

  public getUsuarioEditar(usuarioId: number): Observable<AccionRespuesta>{
    console.log('METODO actualizar');
    const urlGet = this.urlGeneral + this.urlEditarUsuario + usuarioId;
    return this.httpClient.get<AccionRespuesta>(urlGet);
  }

  public crearUsuario(usuario: Usuario){
    console.log('METODO crear');
    const urlPost = this.urlGeneral + this.urlCrearUsuario;
    return this.httpClient.post<AccionRespuesta>(urlPost, usuario, {headers: this.httpHeaders});
  }

  public actualizarUsuario(usuario: Usuario): Observable<AccionRespuesta>{
    console.log('METODO actualizar');
    const urlPost = this.urlGeneral + this.urlEditarUsuario;
    return this.httpClient.post<AccionRespuesta>(urlPost, usuario, {headers: this.httpHeaders});
  }

  public eliminarUsuario(usuarioId: number): Observable<AccionRespuesta>{
    console.log('METODO GET');
    const urlGet = this.urlGeneral + this.urlEliminarUsuario + usuarioId;
    return this.httpClient.get<AccionRespuesta>(urlGet);
  }

  public getUsuarios(): Promise<Usuario[]> {
    console.log('METODO listado');
    const urlPost = this.urlGeneral + this.urlListadoUsuarios;
    return this.httpClient.get<Usuario[]>(urlPost).pipe(map(response => response as Usuario[])).toPromise();
  }
}
