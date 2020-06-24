import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FacturaService } from 'src/app/services/ventas/factura.service';
import { Factura } from 'src/app/model/entitys/factura.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';



@Component({
  selector: 'app-factura',
  templateUrl: './factura.component.html',
  styleUrls: ['./factura.component.css']
})
export class FacturaComponent implements OnInit {

  public factura: Factura;
  private facturaDto: any;
  private facturaId: number;
  private respuestaGetFactura: AccionRespuesta;

  constructor(private facturaService: FacturaService, private router: Router, private activateRouter: ActivatedRoute) {

    this.facturaId = 0;
    this.factura = new Factura();

    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.facturaId = params['id'];
      this.getFactura();
    } );
  }

  getFactura(): void{

    this.facturaService.getFactura(this.facturaId).toPromise().then( (facturaDto) => {
      try
      {
        console.log('Recuperamos el Factura');

        this.respuestaGetFactura = facturaDto;

        if ( this.respuestaGetFactura.resultado )
        {
        console.log('Respuesta: ' +  JSON.stringify(this.respuestaGetFactura.data) );
        console.log('ES: ' + typeof(this.respuestaGetFactura.data));
        // tslint:disable-next-line: no-string-literal
        this.facturaDto = this.respuestaGetFactura.data['facturaDto'];
        this.obtenerFacturaDesdeFacturaDto(this.facturaDto);
        }

      }catch (errores){

        console.log('Se ha producido un error al transformar el Factura' + errores);
      }
      }, (error) => {
      console.log('Error, no se ha podido recuperar el Factura' + error);
      }
    );
  }

  obtenerFacturaDesdeFacturaDto(facturaDto: any): void{

    if ( facturaDto != null)
    {
      this.factura.id = facturaDto.id;
      this.factura.codigo = facturaDto.codigo;
      this.factura.fechaCreacion = facturaDto.fechaCreacion;
      this.factura.fechaInicio = facturaDto.fechaInicio;
      this.factura.fechaFin = facturaDto.fechaFin;
      this.factura.descripcion = facturaDto.descripcion;
      this.factura.baseImponible = facturaDto.baseImponible;
      this.factura.cuotaTributaria = facturaDto.cuotaTributaria;
      this.factura.importeTotal = facturaDto.importeTotal;
    }
  }

  ngOnInit(): void {
  }

}
