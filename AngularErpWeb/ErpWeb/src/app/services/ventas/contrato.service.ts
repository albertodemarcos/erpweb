import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ContratoService {

  constructor() { }

  // METODOS GENERALES

  public getContrato(){
    console.log('METODO obtener');
   }

   public crearContrato(){
    console.log('METODO crear');
   }

   public actualizarContrato(){
    console.log('METODO actualizar');
   }

   public eliminarContrato(){
    console.log('METODO GET');
   }
}
