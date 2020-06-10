import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProveedorService {

  constructor() { }

  // METODOS GENERALES

  public getProveedor(){
    console.log('METODO obtener');
   }

   public crearProveedor(){
    console.log('METODO crear');
   }

   public actualizarProveedor(){
    console.log('METODO actualizar');
   }

   public eliminarProveedor(){
    console.log('METODO GET');
   }
}
