import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class IngresoService {

  constructor() { }

  // METODOS GENERALES

  public getIngreso(){
    console.log('METODO obtener');
   }

   public crearIngreso(){
    console.log('METODO crear');
   }

   public actualizarIngreso(){
    console.log('METODO actualizar');
   }

   public eliminarIngreso(){
    console.log('METODO GET');
   }


}
