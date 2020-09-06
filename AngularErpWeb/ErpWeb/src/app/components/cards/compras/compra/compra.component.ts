import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { CompraService } from 'src/app/services/compras/compra.service';
import { Compra } from 'src/app/model/entitys/compra.model';
import { LineaCompra } from 'src/app/model/entitys/linea-compra.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';
import { Articulo } from 'src/app/model/entitys/articulo.model';


@Component({
  selector: 'app-compra',
  templateUrl: './compra.component.html',
  styleUrls: ['./compra.component.css']
})
export class CompraComponent implements OnInit {

  public compra: Compra;
  private compraDto: any;
  private compraId: number;
  public tiposImpuesto: string[];
  public mapaIva: Map<string, string>;
  private respuestaGetCompra: AccionRespuesta;

  constructor(private compraService: CompraService, private router: Router, private activateRouter: ActivatedRoute) {

    this.compraId = 0;
    this.compra = new Compra();
    this.compra.lineaCompra = new Array<LineaCompra>();
    this.tiposImpuesto = ['IVA_GENERAL', 'IVA_REDUCIDO', 'IVA_SUPER_REDUCIDO'];
    this.mapaIva = new Map<string, string>();
    this.rellenaMapaIva();
    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.compraId = params['id'];
      this.getCompra();
    } );
  }

  getCompra(): void{

    this.compraService.getCompra(this.compraId).toPromise().then( (compraDto) => {
      try
      {
        console.log('Recuperamos el Compra');

        this.respuestaGetCompra = compraDto;

        if ( this.respuestaGetCompra.resultado )
        {
          console.log('Respuesta: ' +  JSON.stringify(this.respuestaGetCompra.data) );
          console.log('ES: ' + typeof(this.respuestaGetCompra.data));
          // tslint:disable-next-line: no-string-literal
          this.compraDto = this.respuestaGetCompra.data['compraDto'];
          this.obtenerCompraDesdeCompraDto(this.compraDto);
        }

      }catch (errores){

        console.log('Se ha producido un error al transformar el Compra' + errores);
      }
      }, (error) => {
      console.log('Error, no se ha podido recuperar el Compra' + error);
      }
    );
  }

  private obtenerCompraDesdeCompraDto(compraDto: any): void{

    if ( compraDto != null)
    {
      this.compra.id = compraDto.id;
      this.compra.codigo = compraDto.codigo;
      this.compra.fechaCompra = compraDto.fechaCompra;
      this.compra.baseImponibleTotal = compraDto.baseImponibleTotal;
      this.compra.importeTotal = compraDto.importeTotal;
      // Lineas de compra
      this.rellenarLineasCompra(compraDto.lineasCompraDto);
    }
  }

  private rellenarLineasCompra(lineasCompraDto: any) {

    if (lineasCompraDto != null)
    {
      // tslint:disable-next-line: prefer-const
      for (let lineaDto of lineasCompraDto )
      {
        // tslint:disable-next-line: prefer-const
        let lineaCompra = new LineaCompra();
        // Linea de compra
        lineaCompra.id = lineaDto.id;
        lineaCompra.compraId = lineaDto.compraId;
        lineaCompra.baseImponible = lineaDto.baseImponible;
        lineaCompra.importeTotal = lineaDto.importeTotal;
        lineaCompra.cantidad = lineaDto.cantidad;
        // Articulo
        lineaCompra.articuloDto = new Articulo();
        lineaCompra.articuloDto.id = lineaDto.articuloDto.id;
        lineaCompra.articuloDto.codigo = lineaDto.articuloDto.codigo;
        lineaCompra.articuloDto.nombre = lineaDto.articuloDto.nombre;
        lineaCompra.articuloDto.baseImponible = lineaDto.articuloDto.baseImponible;
        lineaCompra.articuloDto.impuesto = lineaDto.articuloDto.impuesto;
        lineaCompra.articuloDto.importeTotal = lineaDto.articuloDto.importeTotal;
        // Añadir la linea de compra
        this.compra.lineaCompra.push(lineaCompra);
      }
    }
  }

  public editarCompra(compraId: number): void{
    console.log('Compra CON ID: ' + compraId);
    this.router.navigate(['compras', 'editar-compra', compraId]);
  }

  public borrarCompra(compraId: number): void{

    console.log('Compra CON ID: ' + compraId);

    // Evitamos borrar accidentalmente un Compra
    swal({
      title: 'Eliminar Compra',
      text: '¿Desea eliminar definitivamente este Compra?',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí',
      cancelButtonText: 'No'
    }).then( (resultado) => {
      // Si se pulsa en cancelar, no se continua
      if (!resultado.value) {
        return;
      }

      // Llamamos al servicio de Compras para eliminar el Compra
      this.compraService.eliminarCompra(compraId).toPromise().then( (accionRespuesta) => {

        // Si se ha eliminado correctamente
        if ( accionRespuesta.resultado ) {
        console.log('Se ha eliminado correctamente el Compra');
        swal('Compra eliminada', 'Se ha eliminado el Compra correctamente', 'success').then(() => {
          this.router.navigate( ['compras'] );
        });

        } else {
        console.log('Se ha producido un error al eliminar el Compra');
        swal('Error', 'El Compra no ha podido ser eliminado', 'error');
        }

      }, (errores) => {
        console.log('Se ha producido un error al eliminar el Compra');
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
