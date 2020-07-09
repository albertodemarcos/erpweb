import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { EmpresaService } from 'src/app/services/empresa/empresa.service';
import { Empresa } from 'src/app/model/entitys/empresa.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';



@Component({
  selector: 'app-formulario-empresa',
  templateUrl: './formulario-empresa.component.html',
  styleUrls: ['./formulario-empresa.component.css']
})
export class FormularioEmpresaComponent implements OnInit {

  public empresa: Empresa;
  private empresaId: number;
  private empresaDto: any;
  public tiposSociedadesJuridicas: string[];
  private respuestaGetEmpresa: AccionRespuesta;
  public erroresFormulario: Map<string, object>;

  constructor(private empresaService: EmpresaService, private router: Router, private activateRouter: ActivatedRoute) {

    this.empresa = new Empresa();
    this.tiposSociedadesJuridicas = [ 'SL', 'SA', 'SLU' ];
    this.erroresFormulario = new Map<string, object>();
    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.empresaId = params['id'];
      if (this.empresaId != null){
        this.getEditarEmpresa();
      }
    });
   }

  ngOnInit(): void {

  }

 // Metodos del formulario
 public crearEmpresaFormulario(): void {

  console.log('Estamos dentro del metodo crearEmpresaFormulario()');

  // Si tiene id, llamamos a crear, sino a editar
  if (this.empresa != null && this.empresa.id != null && this.empresa.id !== 0) {

    console.log('Vamos a editar la empresa con ID: ' + this.empresa.id);

    this.empresaService.actualizarEmpresa(this.empresa).subscribe( accionRespuesta => {

      this.respuestaCrearEditarEmpresa(accionRespuesta, true);

    }, (error => {

      swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

    }));

  } else {

    this.empresaService.crearEmpresa(this.empresa).subscribe( accionRespuesta => {

      console.log('Vamos a crear la empresa con codigo: ' + this.empresa.codigo);

      this.respuestaCrearEditarEmpresa(accionRespuesta, false);

    }, (error => {

      swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

    }));
  }

 }

 getEditarEmpresa() {

  this.empresaService.getEmpresa(this.empresaId).toPromise().then( (accionRespuesta) => {
      try
      {
        console.log('Recuperamos la empresa');

        this.respuestaGetEmpresa = accionRespuesta;

        if ( this.respuestaGetEmpresa.resultado )
        {
          console.log('Respuesta: ' +  JSON.stringify(this.respuestaGetEmpresa.data) );
          console.log('ES: ' + typeof(this.respuestaGetEmpresa.data));
          // tslint:disable-next-line: no-string-literal
          this.empresaDto = this.respuestaGetEmpresa.data['empresaDto'];
          this.obtenerEmpresaDesdeEmpresaDto(this.empresaDto);
        }

      }catch (errores){

        console.log('Se ha producido un error al transformar la empresa' + errores);
      }
    }, (error) => {
      console.log('Error, no se ha podido recuperar la empresa' + error);
    }
  );
}

obtenerEmpresaDesdeEmpresaDto(empresaDto: any): void{

  if ( empresaDto != null)
  {
    this.empresa.id = empresaDto.id;
    this.empresa.codigo = empresaDto.codigo;
    this.empresa.nombre = empresaDto.nombre;
    this.empresa.tipoSociedadJuridica = empresaDto.apellidoPrimero;
    this.empresa.cif = empresaDto.cif;
  }
}

respuestaCrearEditarEmpresa(accionRespuesta: AccionRespuesta, esEditarEmpresa: boolean): void {

  console.log('Esta registrado' + accionRespuesta.resultado);
  console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
  // Si el resultado es true, navegamos hasta la vista
  if (accionRespuesta.resultado && accionRespuesta.id !== null ) {

    this.router.navigate(['empresas', 'empresa', accionRespuesta.id]);

    if (esEditarEmpresa != null && esEditarEmpresa ){

      swal('Empresa editado', 'Se ha editado el empresa correctamente', 'success');

     }else{

      swal('Nueva empresa', 'Se ha creado el empresa correctamente', 'success');

     }

  }else{
    // Error
    if ( accionRespuesta != null && accionRespuesta.data != null && accionRespuesta.data !=  null )
    {
      this.erroresFormulario = accionRespuesta.data;
    }else
    {
      swal('Error', 'Se ha producido un error al guardar los datos de la empresa', 'error');
    }
  }

}



}
