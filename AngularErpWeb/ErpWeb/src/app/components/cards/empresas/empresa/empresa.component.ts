import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { EmpresaService } from 'src/app/services/empresa/empresa.service';
import { Empresa } from 'src/app/model/entitys/empresa.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';


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

  editarEmpresa(empresaId: number): void{
    console.log('Empresa CON ID: ' + empresaId);
    this.router.navigate(['empresa', 'editar-empresa', empresaId]);
  }

  borrarEmpresa(empresaId: number): void{

    console.log('Empresa CON ID: ' + empresaId);

    // Evitamos borrar accidentalmente un Empresa
    swal({
      title: 'Eliminar Empresa',
      text: '¿Desea eliminar definitivamente este Empresa?',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí',
      cancelButtonText: 'No'
    }).then( (resultado) => {
      // Si se pulsa en cancelar, no se continua
      if (!resultado.value) {
        return;
      }

      // Llamamos al servicio de Empresas para eliminar el Empresa
      this.empresaService.eliminarEmpresa(empresaId).toPromise().then( (accionRespuesta) => {

        // Si se ha eliminado correctamente
        if ( accionRespuesta.resultado ) {
        console.log('Se ha eliminado correctamente el Empresa');
        swal('Empresa eliminada', 'Se ha eliminado el Empresa correctamente', 'success').then(() =>{
          this.router.navigate( ['empresas'] );
        });

        } else {
        console.log('Se ha producido un error al eliminar el Empresa');
        swal('Error', 'El Empresa no ha podido ser eliminado', 'error');
        }

      }, (errores) => {
        console.log('Se ha producido un error al eliminar el Empresa');
        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');
      } );
    } );

  }

  ngOnInit(): void {
  }

}
