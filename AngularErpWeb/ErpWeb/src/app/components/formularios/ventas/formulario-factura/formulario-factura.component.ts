import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { FacturaService } from 'src/app/services/ventas/factura.service';
import { Factura } from 'src/app/model/entitys/factura.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';


declare var jQuery: any;

@Component({
  selector: 'app-formulario-factura',
  templateUrl: './formulario-factura.component.html',
  styleUrls: ['./formulario-factura.component.css']
})
export class FormularioFacturaComponent implements OnInit {

  public factura: Factura;
  public tiposImpuesto: string[];
  private facturaId: number;
  private facturaDto: any;
  private respuestaGetFactura: AccionRespuesta;
  public erroresFormulario: Map<string, object>;
  public mapaIva: Map<string, string>;
  public titulo: string;
  public botonTitulo: string;

  constructor(private facturaService: FacturaService, private router: Router, private activateRouter: ActivatedRoute) {
    this.factura = new Factura();
    this.erroresFormulario = new Map<string, object>();
    this.tiposImpuesto = ['IVA_GENERAL', 'IVA_REDUCIDO', 'IVA_SUPER_REDUCIDO'];
    this.mapaIva = new Map<string, string>();
    this.titulo = 'Nueva factura';
    this.botonTitulo = 'Crear factura';
    this.rellenaMapaIva();
    this.activateRouter.params.subscribe( params => {
      // tslint:disable-next-line: no-string-literal
      this.facturaId = params['id'];
      if (this.facturaId != null){
        this.getEditarFactura();
      }
    });
   }

  ngOnInit(): void {

    jQuery('#fechaFacturaDatePicker').datepicker({
      dateFormat: 'dd-mm-yy',
      changeMonth: false,
      changeYear: false,
      dayNames: true,
      showButtonPanel: true,
      onClose: () => {
        this.factura.fechaInicio = jQuery('#fechaFacturaDatePicker').datepicker('getDate');
      }
    });

    jQuery.getScript('assets/js/datepicker/datepicker-es.js').done(() => {
      console.log('Se carga el español');
    }).fail(() => {
      console.error('Error, no se ha podido cargar el idioma');
    });

  }

  // Metodos del formulario
  public crearFacturaFormulario(): void {

    console.log('Estamos dentro del metodo crearFacturaFormulario()');

    // Si tiene id, llamamos a crear, sino a editar
    if (this.factura != null && this.factura.id != null && this.factura.id !== 0) {

      console.log('Vamos a editar la factura con ID: ' + this.factura.id);

      this.facturaService.actualizarFactura(this.factura).subscribe( accionRespuesta => {

        this.respuestaCrearEditarFactura(accionRespuesta, true);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));

    } else {

      this.facturaService.crearFactura(this.factura).subscribe( accionRespuesta => {

        console.log('Vamos a crear la factura con codigo: ' + this.factura.codigo);

        this.respuestaCrearEditarFactura(accionRespuesta, false);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));
    }

  }

  public getEditarFactura() {

    this.facturaService.getFactura(this.facturaId).toPromise().then( (accionRespuesta) => {
        try
        {
          this.respuestaGetFactura = accionRespuesta;

          if ( this.respuestaGetFactura.resultado )
          {
            // tslint:disable-next-line: no-string-literal
            this.facturaDto = this.respuestaGetFactura.data['facturaDto'];
            this.obtenerFacturaDesdeFacturaDto(this.facturaDto);
            this.titulo = 'Editar factura';
            this.botonTitulo = 'Editar factura';
          }

        }catch (errores){

          console.log('Se ha producido un error al transformar la factura' + errores);
        }
      }, (error) => {
        console.log('Error, no se ha podido recuperar la factura' + error);
      }
    );
  }

  obtenerFacturaDesdeFacturaDto(facturaDto: any): void{

    if ( facturaDto != null)
    {
      this.factura.id = facturaDto.id;
      this.factura.codigo = facturaDto.codigo;
      // this.factura.fechaCreacion = this.limpiarFecha(facturaDto.fechaCreacion);
      this.factura.fechaInicio = this.limpiarFecha(facturaDto.fechaInicio);
      // this.factura.fechaFin = this.limpiarFecha(facturaDto.fechaFin);
      this.factura.descripcion = facturaDto.descripcion;
      this.factura.baseImponible = facturaDto.baseImponible;
      this.factura.impuesto = facturaDto.impuesto;
      this.factura.importeTotal = facturaDto.importeTotal;
    }
  }

  public respuestaCrearEditarFactura(accionRespuesta: AccionRespuesta, esEditarFactura: boolean): void {

    console.log('Esta registrado' + accionRespuesta.resultado);
    console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
    // Si el resultado es true, navegamos hasta la vista
    if (accionRespuesta.resultado && accionRespuesta.id !== null ) {

      this.router.navigate(['facturas', 'factura', accionRespuesta.id]);

      if (esEditarFactura != null && esEditarFactura ){

        swal('Factura editada', 'Se ha editado la factura correctamente', 'success');

       }else{

        swal('Nuevo factura', 'Se ha creado la factura correctamente', 'success');

       }

    }else{

      if ( accionRespuesta != null && accionRespuesta.data != null && accionRespuesta.data !=  null )
      {
        this.erroresFormulario = accionRespuesta.data;
      }else
      {
        swal('Error', 'Se ha producido un error al guardar los datos de la factura', 'error');
      }
    }
  }

  limpiarFecha(fechaStr: string): Date{

    if (fechaStr != null && fechaStr.trim() !== ''){
      try {
        const fechaLimpia: Date = new Date(fechaStr);
        return fechaLimpia;
      } catch (error) {
        console.log('Error al convertir la fecha' + error);
      }
    }
    return new Date();
  }

  rellenaMapaIva(): void {
    this.mapaIva.set('IVA_GENERAL', 'GENERAL');
    this.mapaIva.set('IVA_REDUCIDO', 'REDUCIDO');
    this.mapaIva.set('IVA_SUPER_REDUCIDO', 'SUPER REDUCIDO');
  }

  public calcularImporteTotal() {
    // calculamos el importe total en base a los impuestos
    if (this.factura.baseImponible != null && this.comprobarImpuesto() )
    {
      switch (this.factura.impuesto){

        case 'IVA_GENERAL':
          this.factura.importeTotal = parseFloat((this.factura.baseImponible * 1.21).toFixed(3));
          break;

        case 'IVA_REDUCIDO':
          this.factura.importeTotal = parseFloat((this.factura.baseImponible * 1.1).toFixed(3));
          break;

        case 'IVA_SUPER_REDUCIDO':
          this.factura.importeTotal = parseFloat((this.factura.baseImponible * 1.04).toFixed(3));
          break;

        default:
          break;
      }
    }
  }

  private comprobarImpuesto(): boolean{
    // Si el impuesto no esta vacio ni nulo
    return (this.factura.impuesto != null && this.factura.impuesto !== 'undefined' && this.factura.impuesto.trim() !== '');
  }





}
