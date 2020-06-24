import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { CompraService } from 'src/app/services/compras/compra.service';
import { Compra } from 'src/app/model/entitys/compra.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';

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

  ngOnInit(): void {
  }

}
