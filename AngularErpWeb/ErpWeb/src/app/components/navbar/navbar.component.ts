import { Component, OnInit } from '@angular/core';
import { UsuarioLoginService } from 'src/app/services/autenticacion/usuario-login.service';
import { Observable } from 'rxjs';

declare var $: any;

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  public visible: boolean;
  private subcriptor = new Observable<boolean>();

  constructor(private usuarioLoginService: UsuarioLoginService) {

  }

  ngOnInit(): void {

    $('.dropdown-menu a.dropdown-toggle').on('click', function(e) {
      if (!$(this).next().hasClass('show')) {
        $(this).parents('.dropdown-menu').first().find('.show').removeClass('show');
      }
      // tslint:disable-next-line: prefer-const
      var $subMenu = $(this).next('.dropdown-menu');
      $subMenu.toggleClass('show');

      // tslint:disable-next-line: only-arrow-functions
      $(this).parents('li.nav-item.dropdown.show').on('hidden.bs.dropdown', function(e) {
        $('.dropdown-submenu .show').removeClass('show');
      });
      return false;
    });

    $('#perfilLogado').hide();

  }

  recargarNavBar(): void {
    console.log('Entramos a recargar el navbar');
    const estaLogado = this.usuarioLoginService.esUsuarioLogado();

    if (estaLogado)
    {
      console.log('Mostramos el navbar');
      $('#perfilLogado').show();
    }
    else
    {
      console.log('Ocultamos el navbar');
      $('#perfilLogado').hide();
    }

  }




}
