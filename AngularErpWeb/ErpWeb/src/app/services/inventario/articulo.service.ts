import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ArticuloService {

  constructor() { }

  // METODOS GENERALES

  public getArticulo(){
    console.log('METODO obtener');
   }

   public crearArticulo(){
    console.log('METODO crear');
   }

   public actualizarArticulo(){
    console.log('METODO actualizar');
   }

   public eliminarArticulo(){
    console.log('METODO GET');
   }
}
