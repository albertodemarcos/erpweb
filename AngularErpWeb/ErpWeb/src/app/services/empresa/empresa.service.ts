import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmpresaService {

  constructor() { }

  // METODOS GENERALES

  public getEmpresa(){
    console.log('METODO obtener');
   }

   public crearEmpresa(){
    console.log('METODO crear');
   }

   public actualizarEmpresa(){
    console.log('METODO actualizar');
   }

   public eliminarEmpresa(){
    console.log('METODO GET');
   }
}
