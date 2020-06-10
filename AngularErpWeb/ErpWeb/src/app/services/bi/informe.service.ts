import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class InformeService {

  constructor() { }

  // METODOS GENERALES

  public getInforme(){
    console.log('METODO obtener');
   }

   public crearInforme(){
    console.log('METODO crear');
   }

   public actualizarInforme(){
    console.log('METODO actualizar');
   }

   public eliminarInforme(){
    console.log('METODO GET');
   }
}
