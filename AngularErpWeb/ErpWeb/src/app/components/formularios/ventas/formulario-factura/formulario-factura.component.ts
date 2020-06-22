import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { FacturaService } from 'src/app/services/ventas/factura.service';
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

  constructor(private facturaService: FacturaService, private router: Router) {
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

    this.facturaService.crearFactura(this.factura).subscribe( accionRespuesta => {
      console.log('Esta registrado' + accionRespuesta.resultado);
      console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
      // Si el resultado es true, navegamos hasta la vista
      if (accionRespuesta.resultado && accionRespuesta.id !== null ) {
        this.router.navigate(['facturas', 'factura', accionRespuesta.id]);
      }
    });

  }

}
