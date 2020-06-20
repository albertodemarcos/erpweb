import { Component, OnInit } from '@angular/core';
import { Venta } from 'src/app/model/entitys/venta.model';

declare var jQuery: any;

@Component({
  selector: 'app-formulario-venta',
  templateUrl: './formulario-venta.component.html',
  styleUrls: ['./formulario-venta.component.css']
})
export class FormularioVentaComponent implements OnInit {

  public venta: Venta;
  private idDatePickerCreacion: string;
  private idDatePickerInicio: string;
  private idDatePickerFin: string;

  constructor() {
    this.venta = new Venta();
    this.idDatePickerCreacion = 'fechaVentaDatePickerCreacion';
    this.idDatePickerInicio = 'fechaVentaDatePickerInicio';
    this.idDatePickerFin = 'fechaVentaDatePickerFin';
  }

  ngOnInit(): void {

    jQuery('#' + this.idDatePickerCreacion + ', #' + this.idDatePickerInicio + ', #' + this.idDatePickerFin).datepicker({
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

  public crearVentaFormulario(): void {

    console.log('Estamos dentro del metodo crear formulario');

  }




}
