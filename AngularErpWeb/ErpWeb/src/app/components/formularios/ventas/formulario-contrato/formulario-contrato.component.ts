import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { ContratoService } from 'src/app/services/ventas/contrato.service';
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

  constructor(private contratoService: ContratoService, private router: Router) {
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

    this.contratoService.crearContrato(this.contrato).subscribe( accionRespuesta => {
      console.log('Esta registrado' + accionRespuesta.resultado);
      console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
      // Si el resultado es true, navegamos hasta la vista
      if (accionRespuesta.resultado && accionRespuesta.id !== null ) {
        this.router.navigate(['contratos', 'contrato', accionRespuesta.id]);
      }
    });

  }




}
