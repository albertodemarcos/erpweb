import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AlmacenService {

  constructor() { }

  // METODOS GENERALES

  public getAlmacen(){
    console.log('METODO obtener');
   }

   public crearAlmacen(){
    console.log('METODO crear');
   }

   public actualizarAlmacen(){
    console.log('METODO actualizar');
   }

   public eliminarAlmacen(){
    console.log('METODO GET');
   }
}
