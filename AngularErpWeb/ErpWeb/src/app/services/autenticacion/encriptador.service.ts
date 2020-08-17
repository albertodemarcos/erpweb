import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class EncriptadorService {

  private palabraEncriptada: string;

  constructor() {

    this.palabraEncriptada = 'AngularApp';
  }

  encriptarTextoParaEnvioHtttp(password: string): string {

    // 1º Comprobamos que la contraseña no viene vacia
    if (password != null || password.trim() !== '')
    {
      // Encriptamos en base64
      return btoa(password);
    }
    // tslint:disable-next-line: no-string-throw
    throw ('Error, el encriptador viene vacio!');
  }



}
