import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AlmacenService {

  constructor() { }

  // METODOS GENERALES

  public getDatos(){
    console.log('METODO obtener');
   }

   public crearDatos(){
    console.log('METODO crear');
   }

   public actualizarDatos(){
    console.log('METODO actualizar');
   }

   public eliminarDatos(){
    console.log('METODO GET');
   }
}
