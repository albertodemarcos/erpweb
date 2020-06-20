import { Component, OnInit } from '@angular/core';

declare var jQuery: any;

import { Compra } from 'src/app/model/entitys/compra.model';

@Component({
  selector: 'app-formulario-compra',
  templateUrl: './formulario-compra.component.html',
  styleUrls: ['./formulario-compra.component.css']
})
export class FormularioCompraComponent implements OnInit {

  public compra: Compra;
  private idDatePicker: string;

  constructor() {
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

  }




}
