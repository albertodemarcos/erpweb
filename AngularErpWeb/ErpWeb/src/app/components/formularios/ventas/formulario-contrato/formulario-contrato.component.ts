import { Component, OnInit } from '@angular/core';
import { Contrato } from 'src/app/model/entitys/contrato.model';

declare var jQuery: any;

@Component({
  selector: 'app-formulario-contrato',
  templateUrl: './formulario-contrato.component.html',
  styleUrls: ['./formulario-contrato.component.css']
})
export class FormularioContratoComponent implements OnInit {

  public contrato: Contrato;
  private idDatePickerCreacion: string;
  private idDatePickerInicio: string;
  private idDatePickerFin: string;

  constructor() {
    this.contrato = new Contrato();
    this.idDatePickerCreacion = 'fechaContratoCreacionDatePicker';
    this.idDatePickerInicio = 'fechaContratoInicioDatePicker';
    this.idDatePickerFin = 'fechaContratoFinDatePicker';
  }

  ngOnInit(): void {

    jQuery('#' + this.idDatePickerCreacion + ',#' + this.idDatePickerInicio + ',#' + this.idDatePickerFin).datepicker({
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
  public crearContratoFormulario(): void {

    console.log('Estamos dentro del metodo crear formulario');

  }




}
