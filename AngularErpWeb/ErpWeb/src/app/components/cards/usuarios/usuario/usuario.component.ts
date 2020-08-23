import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UsuarioService } from 'src/app/services/usuarios/usuario.service';
import { Usuario } from 'src/app/model/entitys/usuario.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';


@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css']
})
export class UsuarioComponent implements OnInit {

  public usuario: Usuario;
  private usuarioDto: any;
  private usuarioId: number;
  private respuestaGetUsuario: AccionRespuesta;

  constructor(private usuarioService: UsuarioService, private router: Router, private activateRouter: ActivatedRoute) {

    this.usuarioId = 0;
    this.usuario = new Usuario();

    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.usuarioId = params['id'];
      this.getUsuario();
    });
  }

  getUsuario(): void{

    this.usuarioService.getUsuario(this.usuarioId).toPromise().then( (accionRespuesta) => {
      try
      {
        console.log('Recuperamos el Usuario');

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

        console.log('Se ha producido un error al transformar el Usuario' + errores);
      }
    }, (error) => {

        console.log('Error, no se ha podido recuperar el Usuario' + error);
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
        this.usuario.nombreCompleto = usuarioDto.nombreCompleto;
        this.usuario.email = usuarioDto.email;
        this.usuario.role = usuarioDto.role;
      }
  }

  editarUsuario(usuarioId: number): void{
    console.log('Usuario CON ID: ' + usuarioId);
    this.router.navigate(['usuarios', 'editar-usuario', usuarioId]);
  }

  borrarUsuario(usuarioId: number): void{

    console.log('Usuario CON ID: ' + usuarioId);

    // Evitamos borrar accidentalmente un Usuario
    swal({
      title: 'Eliminar Usuario',
      text: '¿Desea eliminar definitivamente este Usuario?',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí',
      cancelButtonText: 'No'
    }).then( (resultado) => {
      // Si se pulsa en cancelar, no se continua
      if (!resultado.value) {
        return;
      }

      // Llamamos al servicio de Usuarios para eliminar el Usuario
      this.usuarioService.eliminarUsuario(usuarioId).toPromise().then( (accionRespuesta) => {

        // Si se ha eliminado correctamente
        if ( accionRespuesta.resultado ) {
        console.log('Se ha eliminado correctamente el Usuario');
        swal('Usuario eliminado', 'Se ha eliminado el Usuario correctamente', 'success').then(() =>{
          this.router.navigate( ['usuarios'] );
        });

        } else {
        console.log('Se ha producido un error al eliminar el Usuario');
        swal('Error', 'El Usuario no ha podido ser eliminado', 'error');
        }

      }, (errores) => {
        console.log('Se ha producido un error al eliminar el Usuario');
        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');
      } );
    } );

  }

  ngOnInit(): void {
  }

}




