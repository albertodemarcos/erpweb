import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CompraService {

  constructor() { }

  // METODOS GENERALES

  public getCompra(){
    console.log('METODO obtener');
   }

   public crearCompra(){
    console.log('METODO crear');
   }

   public actualizarCompra(){
    console.log('METODO actualizar');
   }

   public eliminarCompra(){
    console.log('METODO GET');
   }
}
