import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class VentaService {

  constructor() { }

  // METODOS GENERALES

  public getVenta(){
    console.log('METODO obtener');
   }

   public crearVenta(){
    console.log('METODO crear');
   }

   public actualizarVenta(){
    console.log('METODO actualizar');
   }

   public eliminarVenta(){
    console.log('METODO GET');
   }
}
