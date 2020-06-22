import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { CompraService } from 'src/app/services/compras/compra.service';
import { Compra } from 'src/app/model/entitys/compra.model';

declare var jQuery: any;


@Component({
  selector: 'app-formulario-compra',
  templateUrl: './formulario-compra.component.html',
  styleUrls: ['./formulario-compra.component.css']
})
export class FormularioCompraComponent implements OnInit {

  public compra: Compra;
  private idDatePicker: string;

  constructor(private compraService: CompraService, private router: Router) {
    this.compra = new Compra();
    this.idDatePicker = 'fechaCompraDatePicker';
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
  public crearCompraFormulario(): void {

    console.log('Estamos dentro del metodo crear formulario');

    this.compraService.crearCompra(this.compra).subscribe( accionRespuesta => {
      console.log('Esta registrado' + accionRespuesta.resultado);
      console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
      // Si el resultado es true, navegamos hasta la vista
      if (accionRespuesta.resultado && accionRespuesta.id !== null ) {
      this.router.navigate(['compras', 'compra', accionRespuesta.id]);
      }
    });

  }




}
