import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { VentaService } from 'src/app/services/ventas/venta.service';
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

  constructor(private ventaService: VentaService, private router: Router) {
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

    this.ventaService.crearVenta(this.venta).subscribe( accionRespuesta => {
      console.log('Esta registrado' + accionRespuesta.resultado);
      console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
      // Si el resultado es true, navegamos hasta la vista
      if (accionRespuesta.resultado && accionRespuesta.id !== null ) {
        this.router.navigate(['clientes', 'cliente', accionRespuesta.id]);
      }
    });

  }




}
