import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class VehiculoService {

  constructor() { }

  // METODOS GENERALES

  public getVehiculo(){
    console.log('METODO obtener');
   }

   public crearVehiculo(){
    console.log('METODO crear');
   }

   public actualizarVehiculo(){
    console.log('METODO actualizar');
   }

   public eliminarVehiculo(){
    console.log('METODO GET');
   }
}
