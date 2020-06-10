import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GastoService {

  constructor() {
    console.log('Serivicio funcionando!');
   }

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
