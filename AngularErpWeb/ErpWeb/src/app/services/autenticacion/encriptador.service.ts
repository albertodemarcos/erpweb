import { Injectable } from '@angular/core';
import CryptoJS from 'crypto-js';
import Base64 from 'crypto-js/enc-base64';

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
