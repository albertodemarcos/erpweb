import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GastoService {

  constructor() {
    console.log('Serivicio funcionando!');
   }

   // METODOS GENERALES

   public getGasto(){
    console.log('METODO obtener');
   }

   public crearGasto(){
    console.log('METODO crear');
   }

   public actualizarGasto(){
    console.log('METODO actualizar');
   }

   public eliminarGasto(){
    console.log('METODO GET');
   }



}
