import { Component, OnInit } from '@angular/core';
import { Factura } from 'src/app/model/entitys/factura.model';

declare var jQuery: any;

@Component({
  selector: 'app-formulario-factura',
  templateUrl: './formulario-factura.component.html',
  styleUrls: ['./formulario-factura.component.css']
})
export class FormularioFacturaComponent implements OnInit {

  public factura: Factura;
  private idDatePickerCreacion: string;
  private idDatePickerInicio: string;
  private idDatePickerFin: string;

  constructor() {
    this.factura = new Factura();
    this.idDatePickerCreacion = 'fechaFacturaDatePickerCreacion';
    this.idDatePickerInicio = 'fechaFacturaDatePickerInicio';
    this.idDatePickerFin = 'fechaFacturaDatePickerFin';
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
  public crearFacturaFormulario(): void {

    console.log('Estamos dentro del metodo crear formulario');

  }

}
