import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ContratoService } from 'src/app/services/ventas/contrato.service';
import { Contrato } from 'src/app/model/entitys/contrato.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';
import { LineaContrato } from '../../../../model/entitys/linea-contrato.model';
import { Articulo } from 'src/app/model/entitys/articulo.model';


@Component({
  selector: 'app-contrato',
  templateUrl: './contrato.component.html',
  styleUrls: ['./contrato.component.css']
})
export class ContratoComponent implements OnInit {

  public contrato: Contrato;
  private contratoDto: any;
  private contratoId: number;
  public tiposImpuesto: string[];
  public mapaIva: Map<string, string>;
  private respuestaGetContrato: AccionRespuesta;

  constructor(private contratoService: ContratoService, private router: Router, private activateRouter: ActivatedRoute) {

    this.contratoId = 0;
    this.contrato = new Contrato();
    this.contrato.lineaContrato = new Array<LineaContrato>();
    this.tiposImpuesto = ['IVA_GENERAL', 'IVA_REDUCIDO', 'IVA_SUPER_REDUCIDO'];
    this.mapaIva = new Map<string, string>();
    this.rellenaMapaIva();
    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.contratoId = params['id'];
      this.getContrato();
    } );
  }

  public getContrato(): void{

    this.contratoService.getContrato(this.contratoId).toPromise().then( (contratoDto) => {
      try
      {
        console.log('Recuperamos el Contrato');

        this.respuestaGetContrato = contratoDto;

        if ( this.respuestaGetContrato.resultado )
        {
          console.log('Respuesta: ' +  JSON.stringify(this.respuestaGetContrato.data) );
          console.log('ES: ' + typeof(this.respuestaGetContrato.data));
          // tslint:disable-next-line: no-string-literal
          this.contratoDto = this.respuestaGetContrato.data['contratoDto'];
          this.obtenerContratoDesdeContratoDto(this.contratoDto);
        }

      }catch (errores){

        console.log('Se ha producido un error al transformar el Contrato' + errores);
      }
      }, (error) => {
      console.log('Error, no se ha podido recuperar el Contrato' + error);
      }
    );
  }

  private obtenerContratoDesdeContratoDto(contratoDto: any): void{

    if ( contratoDto != null)
    {
      this.contrato.id = contratoDto.id;
      this.contrato.codigo = contratoDto.codigo;
      this.contrato.fechaCreacion = contratoDto.fechaCreacion;
      this.contrato.fechaInicio = contratoDto.fechaInicio;
      this.contrato.fechaFin = contratoDto.fechaFin;
      this.contrato.descripcion = contratoDto.descripcion;
      this.contrato.baseImponibleTotal = contratoDto.baseImponibleTotal;
      this.contrato.impuesto = contratoDto.impuesto;
      this.contrato.importeTotal = contratoDto.importeTotal;
      // Lineas de contrato
      this.rellenarLineasContrato(contratoDto.lineasContratoDto);
    }
  }

  private rellenarLineasContrato(LineaContratoDto: any) {

    if (LineaContratoDto != null)
    {
      // tslint:disable-next-line: prefer-const
      for (let lineaDto of LineaContratoDto )
      {
        // tslint:disable-next-line: prefer-const
        let lineaContrato = new LineaContrato();
        // Linea de compra
        lineaContrato.id = lineaDto.id;
        lineaContrato.compraId = lineaDto.compraId;
        lineaContrato.baseImponible = lineaDto.baseImponible;
        lineaContrato.importeTotal = lineaDto.importeTotal;
        lineaContrato.cantidad = lineaDto.cantidad;
        // Articulo
        lineaContrato.articuloDto = new Articulo();
        lineaContrato.articuloDto.id = lineaDto.articuloDto.id;
        lineaContrato.articuloDto.codigo = lineaDto.articuloDto.codigo;
        lineaContrato.articuloDto.nombre = lineaDto.articuloDto.nombre;
        lineaContrato.articuloDto.baseImponible = lineaDto.articuloDto.baseImponible;
        lineaContrato.articuloDto.impuesto = lineaDto.articuloDto.impuesto;
        lineaContrato.articuloDto.importeTotal = lineaDto.articuloDto.importeTotal;
        // Añadir la linea de compra
        this.contrato.lineaContrato.push(lineaContrato);
      }
    }
  }

  public editarContrato(contratoId: number): void{
    console.log('Contrato CON ID: ' + contratoId);
    this.router.navigate(['contratos', 'editar-contrato', contratoId]);
  }

  public borrarContrato(contratoId: number): void{

    console.log('Contrato CON ID: ' + contratoId);

    // Evitamos borrar accidentalmente un Contrato
    swal({
      title: 'Eliminar Contrato',
      text: '¿Desea eliminar definitivamente este Contrato?',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí',
      cancelButtonText: 'No'
    }).then( (resultado) => {
      // Si se pulsa en cancelar, no se continua
      if (!resultado.value) {
        return;
      }

      // Llamamos al servicio de Contratos para eliminar el Contrato
      this.contratoService.eliminarContrato(contratoId).toPromise().then( (accionRespuesta) => {

        // Si se ha eliminado correctamente
        if ( accionRespuesta.resultado ) {
        console.log('Se ha eliminado correctamente el Contrato');
        swal('Contrato eliminado', 'Se ha eliminado el Contrato correctamente', 'success').then(() => {
          this.router.navigate( ['contratos'] );
        });

        } else {
        console.log('Se ha producido un error al eliminar el Contrato');
        swal('Error', 'El Contrato no ha podido ser eliminado', 'error');
        }

      }, (errores) => {
        console.log('Se ha producido un error al eliminar el Contrato');
        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');
      } );
    } );

  }

  private rellenaMapaIva(): void{
    this.mapaIva.set('IVA_GENERAL', 'GENERAL (21%)');
    this.mapaIva.set('IVA_REDUCIDO', 'REDUCIDO (10%)');
    this.mapaIva.set('IVA_SUPER_REDUCIDO', 'SUPER REDUCIDO (4%)');
  }

  ngOnInit(): void {
  }

}
