import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { EmpresaService } from 'src/app/services/empresa/empresa.service';
import { Empresa } from 'src/app/model/entitys/empresa.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';



@Component({
  selector: 'app-empresa',
  templateUrl: './empresa.component.html',
  styleUrls: ['./empresa.component.css']
})
export class EmpresaComponent implements OnInit {

  public empresa: Empresa;
  private empresaDto: any;
  private empresaId: number;
  private respuestaGetEmpresa: AccionRespuesta;

  constructor(private empresaService: EmpresaService, private router: Router, private activateRouter: ActivatedRoute) {

    this.empresaId = 0;
    this.empresa = new Empresa();

    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.empresaId = params['id'];
      this.getEmpresa();
    } );
  }

  getEmpresa(): void{

    this.empresaService.getEmpresa(this.empresaId).toPromise().then( (empresaDto) => {
      try
      {
        console.log('Recuperamos el Empresa');

        this.respuestaGetEmpresa = empresaDto;

        if ( this.respuestaGetEmpresa.resultado )
        {
        console.log('Respuesta: ' +  JSON.stringify(this.respuestaGetEmpresa.data) );
        console.log('ES: ' + typeof(this.respuestaGetEmpresa.data));
        // tslint:disable-next-line: no-string-literal
        this.empresaDto = this.respuestaGetEmpresa.data['empresaDto'];
        this.obtenerEmpresaDesdeEmpresaDto(this.empresaDto);
        }

      }catch (errores){

        console.log('Se ha producido un error al transformar el Empresa' + errores);
      }
      }, (error) => {
      console.log('Error, no se ha podido recuperar el Empresa' + error);
      }
    );
  }

   obtenerEmpresaDesdeEmpresaDto(empresaDto: any): void{

      if ( empresaDto != null)
      {
        this.empresa.id = empresaDto.id;
        this.empresa.codigo = empresaDto.codigo;
        this.empresa.nombre = empresaDto.nombre;
        this.empresa.tipoSociedadJuridica = empresaDto.tipoSociedadJuridica;
        this.empresa.cif = empresaDto.cif;
        this.empresa.idioma = empresaDto.idioma;
      }
    }

  ngOnInit(): void {
  }

}
