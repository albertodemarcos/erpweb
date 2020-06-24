import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private urlGeneral: string;
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
  private urlCrearUsuario: string;
  private urlListadoUsuarios: string;
  private urlGetUsuario: string;

  constructor(private httpClient: HttpClient) {
    this.urlGeneral = 'http://localhost:8080';
    this.urlCrearUsuario = '/usuarios/crearUsuario';
    this.urlListadoUsuarios = '/usuarios/listado.json';
    this.urlGetUsuario = '/usuarios/usuario/';
  }

  // METODOS GENERALES
  public getUsuario(id: number): Observable<AccionRespuesta> {
    console.log('METODO obtener');
    const urlGet = this.urlGeneral + this.urlGetUsuario + id;
    return this.httpClient.get<AccionRespuesta>(urlGet);
  }

   public crearUsuario(){
    console.log('METODO crear');
   }

   public actualizarUsuario(){
    console.log('METODO actualizar');
   }

   public eliminarUsuario(){
    console.log('METODO GET');
   }
}
