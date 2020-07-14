import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EncriptadorService {

  private encriptado: string;

  constructor() {

    this.encriptado = 'AngularApp';
  }

  encriptarTextoParaEnvioHtttp(encriptador: string): string {

    if (encriptador != null && encriptador.trim() !== '')
    {
      return btoa( this.encriptado + encriptador );
    }

    // tslint:disable-next-line: no-string-throw
    throw ('Error, el encriptador viene vacio!');
  }



}
