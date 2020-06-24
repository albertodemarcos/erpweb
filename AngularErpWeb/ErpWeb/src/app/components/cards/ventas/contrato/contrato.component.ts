import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ContratoService } from 'src/app/services/ventas/contrato.service';
import { Contrato } from 'src/app/model/entitys/contrato.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';



@Component({
  selector: 'app-contrato',
  templateUrl: './contrato.component.html',
  styleUrls: ['./contrato.component.css']
})
export class ContratoComponent implements OnInit {

  public contrato: Contrato;
  private contratoDto: any;
  private contratoId: number;
  private respuestaGetContrato: AccionRespuesta;

  constructor(private contratoService: ContratoService, private router: Router, private activateRouter: ActivatedRoute) {

    this.contratoId = 0;
    this.contrato = new Contrato();

    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.contratoId = params['id'];
      this.getContrato();
    } );
  }

  getContrato(): void{

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

   obtenerContratoDesdeContratoDto(contratoDto: any): void{

    if ( contratoDto != null)
    {
      this.contrato.id = contratoDto.id;
      this.contrato.codigo = contratoDto.codigo;
      this.contrato.fechaCreacion = contratoDto.fechaCreacion;
      this.contrato.fechaInicio = contratoDto.fechaInicio;
      this.contrato.fechaFin = contratoDto.fechaFin;
      this.contrato.descripcion = contratoDto.descripcion;
      this.contrato.baseImponibleTotal = contratoDto.baseImponibleTotal;
      this.contrato.importeTotal = contratoDto.importeTotal;
    }
  }

  ngOnInit(): void {
  }

}
