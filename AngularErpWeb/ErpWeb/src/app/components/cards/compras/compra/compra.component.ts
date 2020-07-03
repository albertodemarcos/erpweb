import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { CompraService } from 'src/app/services/compras/compra.service';
import { Compra } from 'src/app/model/entitys/compra.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';

@Component({
  selector: 'app-compra',
  templateUrl: './compra.component.html',
  styleUrls: ['./compra.component.css']
})
export class CompraComponent implements OnInit {

  public compra: Compra;
  private compraDto: any;
  private compraId: number;
  private respuestaGetCompra: AccionRespuesta;

  constructor(private compraService: CompraService, private router: Router, private activateRouter: ActivatedRoute) {

    this.compraId = 0;
    this.compra = new Compra();

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

  obtenerCompraDesdeCompraDto(compraDto: any): void{

    if ( compraDto != null)
    {
      this.compra.id = compraDto.id;
      this.compra.codigo = compraDto.codigo;
      this.compra.fechaCompra = compraDto.fechaCompra;
      this.compra.articulo = compraDto.articulo;
      this.compra.cantidad = compraDto.cantidad;
      this.compra.baseImponibleTotal = compraDto.baseImponibleTotal;
      this.compra.impuesto = compraDto.impuesto;
      this.compra.importeTotal = compraDto.importeTotal;
    }
  }

  editarCompra(compraId: number): void{
    console.log('Compra CON ID: ' + compraId);
    this.router.navigate(['compras', 'editar-compra', compraId]);
  }

  borrarCompra(compraId: number): void{

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
        swal('Compra elimninado', 'Se ha eliminado el Compra correctamente', 'success').then(() => {
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

  ngOnInit(): void {
  }

}
