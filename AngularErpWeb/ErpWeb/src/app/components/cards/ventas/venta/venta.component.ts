import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { VentaService } from 'src/app/services/ventas/venta.service';
import { Venta } from 'src/app/model/entitys/venta.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';



@Component({
  selector: 'app-venta',
  templateUrl: './venta.component.html',
  styleUrls: ['./venta.component.css']
})
export class VentaComponent implements OnInit {

  public venta: Venta;
  private ventaDto: any;
  private ventaId: number;
  private respuestaGetVenta: AccionRespuesta;

  constructor(private ventaService: VentaService, private router: Router, private activateRouter: ActivatedRoute) {

    this.ventaId = 0;
    this.venta = new Venta();

    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.ventaId = params['id'];
      this.getVenta();
    } );
  }

  getVenta(): void{

    this.ventaService.getVenta(this.ventaId).toPromise().then( (ventaDto) => {
      try
      {
        console.log('Recuperamos el Venta');

        this.respuestaGetVenta = ventaDto;

        if ( this.respuestaGetVenta.resultado )
        {
        console.log('Respuesta: ' +  JSON.stringify(this.respuestaGetVenta.data) );
        console.log('ES: ' + typeof(this.respuestaGetVenta.data));
        // tslint:disable-next-line: no-string-literal
        this.ventaDto = this.respuestaGetVenta.data['ventaDto'];
        this.obtenerVentaDesdeVentaDto(this.ventaDto);
        }

      }catch (errores){

        console.log('Se ha producido un error al transformar el Venta' + errores);
      }
      }, (error) => {
      console.log('Error, no se ha podido recuperar el Venta' + error);
      }
    );
  }


   obtenerVentaDesdeVentaDto(ventaDto: any): void{

    if ( ventaDto != null)
    {
      this.venta.id = ventaDto.id;
      this.venta.codigo = ventaDto.codigo;
      this.venta.fechaCreacion = ventaDto.fechaCreacion;
      this.venta.fechaInicio = ventaDto.fechaInicio;
      this.venta.fechaFin = ventaDto.fechaFin;
      this.venta.descripcion = ventaDto.descripcion;
      this.venta.baseImponibleTotal = ventaDto.baseImponibleTotal;
      this.venta.importeTotal = ventaDto.importeTotal;
    }
  }

  ngOnInit(): void {
  }

}
