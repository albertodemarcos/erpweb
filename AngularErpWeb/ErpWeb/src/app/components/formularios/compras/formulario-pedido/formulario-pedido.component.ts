import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { PedidoService } from 'src/app/services/compras/pedido.service';
import { Pedido } from 'src/app/model/entitys/pedido.model';

declare var jQuery: any;

@Component({
  selector: 'app-formulario-pedido',
  templateUrl: './formulario-pedido.component.html',
  styleUrls: ['./formulario-pedido.component.css']
})
export class FormularioPedidoComponent implements OnInit {

  public pedido: Pedido;
  private idDatePicker: string;

  constructor(private pedidoService: PedidoService, private router: Router) {
    this.pedido = new Pedido();
    this.idDatePicker = 'fechaPedidoDatePicker';
   }

  ngOnInit(): void {

    jQuery('#' + this.idDatePicker).datepicker({
      dateFormat: 'dd-mm-yy',
      changeMonth: false,
      changeYear: false,
      dayNames: true,
      duration: 'slow'
    });

    jQuery.getScript('assets/js/datepicker/datepicker-es.js').done(() => {
      console.log('Se carga el español');
    }).fail(() => {
      console.error('Error, no se ha podido cargar el idioma');
    });
  }

  // Metodos del formulario
  public crearPedidoFormulario(): void {

    console.log('Estamos dentro del metodo crear formulario');

    this.pedidoService.crearPedido(this.pedido).subscribe( accionRespuesta => {
      console.log('Esta registrado' + accionRespuesta.resultado);
      console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
      // Si el resultado es true, navegamos hasta la vista
      if (accionRespuesta.resultado && accionRespuesta.id !== null ) {
        this.router.navigate(['clientes', 'cliente', accionRespuesta.id]);
      }
    });

  }




}
