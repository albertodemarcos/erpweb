import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor() { }

  // METODOS GENERALES

  public getUsuario(){
    console.log('METODO obtener');
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
