import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PedidoService {

  constructor() { }

  // METODOS GENERALES

  public getPedido(){
    console.log('METODO obtener');
   }

   public crearPedido(){
    console.log('METODO crear');
   }

   public actualizarPedido(){
    console.log('METODO actualizar');
   }

   public eliminarPedido(){
    console.log('METODO GET');
   }
}
