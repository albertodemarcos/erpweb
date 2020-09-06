import { Component, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { UsuarioLoginService } from 'src/app/services/autenticacion/usuario-login.service';
import { Observable } from 'rxjs';

declare var $: any;

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit, OnChanges {

  public visible: boolean;
  public usuarioId: number;
  private subcriptor = new Observable<boolean>();

  constructor(private usuarioLoginService: UsuarioLoginService) {

    this.usuarioId = Number.parseInt( sessionStorage.getItem('userId'), 0);
  }
  ngOnChanges(changes: SimpleChanges): void {
    this.usuarioId = Number.parseInt( sessionStorage.getItem('userId'), 0);
  }

  ngOnInit(): void {

    $('.dropdown-menu a.dropdown-toggle').on('click', function(e: any) {
      if (!$(this).next().hasClass('show')) {
        $(this).parents('.dropdown-menu').first().find('.show').removeClass('show');
      }
      // tslint:disable-next-line: prefer-const no-var-keyword
      var $subMenu = $(this).next('.dropdown-menu');
      $subMenu.toggleClass('show');

      // tslint:disable-next-line: only-arrow-functions no-shadowed-variable
      $(this).parents('li.nav-item.dropdown.show').on('hidden.bs.dropdown', function(e: any) {
        $('.dropdown-submenu .show').removeClass('show');
      });
      return false;
    });
    console.log('Entro al navbar');
    this.recargarNavBar();
  }

  public recargarNavBar(): void {
    console.log('Entramos a recargar el navbar');
    this.usuarioLoginService.mostrarCuentaUsuarioLogin();
    this.usuarioLoginService.mostrarPanelAdministrador();
    this.usuarioId = Number.parseInt( sessionStorage.getItem('userId'), 0);
  }

}
