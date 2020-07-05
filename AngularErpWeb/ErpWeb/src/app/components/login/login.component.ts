import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../../services/autenticacion/auth.service';
import { AccionRespuesta } from '../../model/utiles/accion-respuesta.model';
import { Usuario } from '../../model/entitys/usuario.model';
import swal from 'sweetalert2';





@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public titulo: string;

  public usuario: Usuario;

  constructor(private authService: AuthService, private router: Router, private activateRouter: ActivatedRoute) {

    this.titulo = 'Bienvenido';
    this.usuario = new Usuario();
  }

  getLoginUsuario(): void {

    console.log('Entramos a validar el usuario: ' + JSON.stringify(this.usuario) );

    // Comprobamos que el usuario no deja nigun campo sin rellenar
    if ( !this.comprobarLoginCorrecto() )
    {
      console.log('ERROR');
      return;
    }

    this.authService.login(this.usuario).subscribe( accionRespuesta => {
      console.log('RESPUESTA: ' + JSON.stringify(accionRespuesta));
      swal('ErpWeb', 'Registro correcto', 'success');
      this.router.navigate(['inicio']);
    });


  }

  /* METODOS AUXILIARES*/

  comprobarLoginCorrecto(): boolean {

    // Error el nombre de usuario no se ha introducido correctamente
    if ( (this.usuario.username == null || this.usuario.username.trim() === '')
          && (this.usuario.password == null || this.usuario.password.trim() === '') )
    {
      // Error, falta todo
      swal('Error', 'Introduce el nombre de usuario y contraseña', 'error');
      return false;

    } else if (this.usuario.username == null || this.usuario.username.trim() === '')
    {
      // Error, falta el usuario
      swal('Error', 'Introduce el nombre de usuario', 'error');
      return false;

    } else if (this.usuario.password == null || this.usuario.password.trim() === '')
    {
      // Error falta la contraseña
      swal('Error', 'Introduce la contraseña', 'error');
      return false;
    }

    return true;
  }


  ngOnInit(): void {
  }

}
