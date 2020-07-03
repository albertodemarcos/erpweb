import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { ContratoService } from 'src/app/services/ventas/contrato.service';
import { Contrato } from 'src/app/model/entitys/contrato.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';


declare var jQuery: any;

@Component({
  selector: 'app-formulario-contrato',
  templateUrl: './formulario-contrato.component.html',
  styleUrls: ['./formulario-contrato.component.css']
})
export class FormularioContratoComponent implements OnInit {

  public contrato: Contrato;
  public tiposImpuesto: string[];
  private contratoId: number;
  private contratoDto: any;
  private respuestaGetContrato: AccionRespuesta;

  constructor(private contratoService: ContratoService, private router: Router, private activateRouter: ActivatedRoute) {
    this.contrato = new Contrato();
    this.tiposImpuesto = ['IVA_GENERAL', 'IVA_REDUCIDO', 'IVA_SUPER_REDUCIDO'];
    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.contratoId = params['id'];
      if (this.contratoId != null){
        this.getEditarContrato();
      }
    });
  }

  ngOnInit(): void {

  }

  // Metodos del formulario
  public crearContratoFormulario(): void {

    console.log('Estamos dentro del metodo crearcontratoFormulario()');

    // Si tiene id, llamamos a crear, sino a editar
    if (this.contrato != null && this.contrato.id != null && this.contrato.id !== 0) {

      console.log('Vamos a editar el contrato con ID: ' + this.contrato.id);

      this.contratoService.actualizarContrato(this.contrato).subscribe( accionRespuesta => {

        this.respuestaCrearEditarContrato(accionRespuesta, true);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));

    } else {

      this.contratoService.crearContrato(this.contrato).subscribe( accionRespuesta => {

        console.log('Vamos a crear el contrato con codigo: ' + this.contrato.codigo);

        this.respuestaCrearEditarContrato(accionRespuesta, false);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));
    }

  }

  getEditarContrato() {

    this.contratoService.getContrato(this.contratoId).toPromise().then( (accionRespuesta) => {
        try
        {
          console.log('Recuperamos el contrato');

          this.respuestaGetContrato = accionRespuesta;

          if ( this.respuestaGetContrato.resultado )
          {
            console.log('Respuesta: ' +  JSON.stringify(this.respuestaGetContrato.data) );
            console.log('ES: ' + typeof(this.respuestaGetContrato.data));
            // tslint:disable-next-line: no-string-literal
            this.contratoDto = this.respuestaGetContrato.data['contratoDto'];
            this.obtenerContratoDesdeContratoDto(this.contratoDto);
          }

        }catch (errores){

          console.log('Se ha producido un error al transformar el contrato' + errores);
        }
      }, (error) => {
        console.log('Error, no se ha podido recuperar el contrato' + error);
      }
    );
  }

  obtenerContratoDesdeContratoDto(contratoDto: any): void{

    if ( contratoDto != null)
    {
      this.contrato.id = contratoDto.id;
      this.contrato.codigo = contratoDto.codigo;
      this.contrato.fechaCreacion = this.limpiarFecha(contratoDto.fechaCreacion);
      this.contrato.fechaInicio = this.limpiarFecha(contratoDto.fechaInicio);
      this.contrato.fechaFin = this.limpiarFecha(contratoDto.fechaFin);
      this.contrato.descripcion = contratoDto.descripcion;
      this.contrato.baseImponibleTotal = contratoDto.baseImponibleTotal;
      this.contrato.impuesto = contratoDto.impuesto;
      this.contrato.importeTotal = contratoDto.importeTotal;
    }
  }

  respuestaCrearEditarContrato(accionRespuesta: AccionRespuesta, esEditarContrato: boolean): void {

    console.log('Esta registrado' + accionRespuesta.resultado);
    console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
    // Si el resultado es true, navegamos hasta la vista
    if (accionRespuesta.resultado && accionRespuesta.id !== null ) {

      this.router.navigate(['contratos', 'contrato', accionRespuesta.id]);

      if (esEditarContrato != null && esEditarContrato ){

        swal('contrato editado', 'Se ha editado el contrato correctamente', 'success');

       }else{

        swal('Nuevo contrato', 'Se ha creado el contrato correctamente', 'success');
       }

    }else{
      // Error
      swal('Contrato', 'Se ha producido un error al guardar el contrato', 'success');
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


}
