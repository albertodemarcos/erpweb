import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
// Autenticacion
import { UsuarioLoginService } from 'src/app/services/autenticacion/usuario-login.service';
import { EncriptadorService } from 'src/app/services/autenticacion/encriptador.service';
import { AutenticacionRequest } from 'src/app/model/entitys/autenticacion-request.model';
// Otros
import { Usuario } from '../../model/entitys/usuario.model';
import swal from 'sweetalert2';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  public titulo: string;
  public usuario: Usuario;
  private respuesta: any;
  private token: any;

  constructor(
    private usuarioLoginService: UsuarioLoginService,
    private encriptadorService: EncriptadorService,
    private router: Router) {

  }

  public salirApp(): void{
    sessionStorage.removeItem('username');
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('rol');
    sessionStorage.removeItem('userId');
  }

  ngOnInit(): void {

    this.salirApp();
    this.usuarioLoginService.mostrarCuentaUsuarioLogin();
    this.usuarioLoginService.mostrarPanelAdministrador();
    this.router.navigate(['inicio']);
  }

}
