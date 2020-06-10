import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {

  constructor() { }

  // METODOS GENERALES

  public getEmpleado(){
    console.log('METODO obtener');
   }

   public crearEmpleado(){
    console.log('METODO crear');
   }

   public actualizarEmpleado(){
    console.log('METODO actualizar');
   }

   public eliminarEmpleado(){
    console.log('METODO GET');
   }
}
