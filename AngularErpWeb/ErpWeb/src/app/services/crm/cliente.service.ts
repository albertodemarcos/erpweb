import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor() { }

  // METODOS GENERALES

  public getCliente(){
    console.log('METODO obtener');
   }

   public crearCliente(){
    console.log('METODO crear');
   }

   public actualizarCliente(){
    console.log('METODO actualizar');
   }

   public eliminarCliente(){
    console.log('METODO GET');
   }
}
