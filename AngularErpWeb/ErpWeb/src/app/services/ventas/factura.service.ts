import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FacturaService {

  constructor() { }

  // METODOS GENERALES

  public getFactura(){
    console.log('METODO obtener');
   }

   public crearFactura(){
    console.log('METODO crear');
   }

   public actualizarFactura(){
    console.log('METODO actualizar');
   }

   public eliminarFactura(){
    console.log('METODO GET');
   }
}
