import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { UsuarioService } from 'src/app/services/usuarios/usuario.service';
import { Usuario } from 'src/app/model/entitys/usuario.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';


@Component({
  selector: 'app-formulario-usuario',
  templateUrl: './formulario-usuario.component.html',
  styleUrls: ['./formulario-usuario.component.css']
})
export class FormularioUsuarioComponent implements OnInit {

  public usuario: Usuario;
  private usuarioId: number;
  private usuarioDto: any;
  private respuestaGetUsuario: AccionRespuesta;

  constructor(private usuarioService: UsuarioService, private router: Router, private activateRouter: ActivatedRoute) {

    this.usuario = new Usuario();

    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.usuarioId = params['id'];
      if (this.usuarioId != null){
        this.getEditarUsuario();
      }
    });
  }

  // Metodos del formulario
  public crearUsuarioFormulario(): void {

    console.log('Estamos dentro del metodo crear formulario');

    this.usuarioService.crearUsuario(this.usuario).subscribe( accionRespuesta => {
      console.log('Esta registrado' + accionRespuesta.resultado);
      console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
      // Si el resultado es true, navegamos hasta la vista
      if (accionRespuesta.resultado && accionRespuesta.id !== null ) {
        this.router.navigate(['usuarios', 'usuario', accionRespuesta.id]);
      }
    });

    console.log('Estamos dentro del metodo crearUsuarioFormulario()');

    // Si tiene id, llamamos a crear, sino a editar
    if (this.usuario != null && this.usuario.id != null && this.usuario.id !== 0) {

      console.log('Vamos a editar el usuario con ID: ' + this.usuario.id);

      this.usuarioService.actualizarUsuario(this.usuario).subscribe( accionRespuesta => {

        this.respuestaCrearEditarUsuario(accionRespuesta, true);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));

    } else {

      this.usuarioService.crearUsuario(this.usuario).subscribe( accionRespuesta => {

        console.log('Vamos a crear el usuario con codigo: ' + this.usuario.codigo);

        this.respuestaCrearEditarUsuario(accionRespuesta, false);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));
    }

  }

  getEditarUsuario() {

    this.usuarioService.getUsuario(this.usuarioId).toPromise().then( (accionRespuesta) => {
        try
        {
          console.log('Recuperamos el usuario');

          this.respuestaGetUsuario = accionRespuesta;

          if ( this.respuestaGetUsuario.resultado )
          {
            console.log('Respuesta: ' +  JSON.stringify(this.respuestaGetUsuario.data) );
            console.log('ES: ' + typeof(this.respuestaGetUsuario.data));
            // tslint:disable-next-line: no-string-literal
            this.usuarioDto = this.respuestaGetUsuario.data['usuarioDto'];
            this.obtenerUsuarioDesdeUsuarioDto(this.usuarioDto);
          }

        }catch (errores){

          console.log('Se ha producido un error al transformar el usuario' + errores);
        }
      }, (error) => {
        console.log('Error, no se ha podido recuperar el usuario' + error);
      }
    );
  }

  obtenerUsuarioDesdeUsuarioDto(usuarioDto: any): void{

    if ( usuarioDto != null)
    {
      this.usuario.id = usuarioDto.id;
      this.usuario.codigo = usuarioDto.codigo;
      this.usuario.username = usuarioDto.username;
      this.usuario.password = usuarioDto.password;
    }
  }

  respuestaCrearEditarUsuario(accionRespuesta: AccionRespuesta, esEditarUsuario: boolean): void {

    console.log('Esta registrado' + accionRespuesta.resultado);
    console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
    // Si el resultado es true, navegamos hasta la vista
    if (accionRespuesta.resultado && accionRespuesta.id !== null ) {

      this.router.navigate(['usuarios', 'usuario', accionRespuesta.id]);

      if (esEditarUsuario != null && esEditarUsuario ){

        swal('Usuario editado', 'Se ha editado el usuario correctamente', 'success');

       }else{

        swal('Nuevo usuario', 'Se ha creado el usuario correctamente', 'success');

       }

    }else{

      if (esEditarUsuario != null && esEditarUsuario ){

        swal('Usuario editado', 'Se ha editado el usuario correctamente', 'success');

       }else{

        swal('Nuevo usuario', 'Se ha creado el usuario correctamente', 'success');

      }
    }

  }

  ngOnInit(): void {
  }

}

