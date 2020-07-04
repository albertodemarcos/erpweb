import { Component, OnInit , AfterViewInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UsuarioService } from 'src/app/services/usuarios/usuario.service';
import { Usuario } from 'src/app/model/entitys/usuario.model';

declare var jQuery: any;


@Component({
  selector: 'app-listado-usuarios',
  templateUrl: './listado-usuarios.component.html',
  styleUrls: ['./listado-usuarios.component.css']
})
export class ListadoUsuariosComponent implements OnInit, AfterViewInit {

  public tituloListado: string;
  private jqGridId: string;
  private jqGridPagerId: string;
  private jqGridColNames: string[];
  private jqGridColModel: {};
  private jqGridData: Usuario[];

  constructor(private usuarioService: UsuarioService, private router: Router, private activatedRoute: ActivatedRoute) {
    this.tituloListado = 'Listado de Usuarios';
    this.jqGridId = 'usuarios-grid';
    this.jqGridPagerId = 'usuarios-pager';
    this.jqGridColNames = ['ID', 'Ver', 'Código', 'Nombre', 'Usuario', 'Contraseña', '' ];
    this.jqGridColModel = [
      { name: 'id', index: '', hidden: true},
      { name: '', index: '', width: '60', height: '50', align: 'center', search: false, sortable: false, formatter:
        () => {
          return '<button class="btn btn-primary btn-xs" style="margin: 0%; width: 15 px; height: 30px">' +
          '<i class="fa fa-search-minus" aria-hidden="true"></i></button>';
        }
      },
      { name: 'codigo', index: '', width: '', search: true, sortable: true },
      { name: 'nombreCompleto', index: '', width: '', search: true, sortable: true },
      { name: 'usuario', index: '', width: '', search: true, sortable: true },
      { name: 'pass', index: '', width: '', search: true, sortable: true },
      { name: 'role', index: '', width: '', search: true, sortable: true, hidden: true}
    ];
    this.jqGridData = new Array<Usuario>();
  }

  getListadoUsuarios(): void{

    console.log('Entramos en el metodo getListadoUsuarios()');

    this.usuarioService.getUsuarios().then( (usuarios) => {
        try {
          // Introducimos los datos
          usuarios.forEach(usuario => this.jqGridData.push(usuario));
          // Reload JqGrid
          jQuery('#' + this.jqGridId).jqGrid('setGridParam', {data: this.jqGridData}).trigger('reloadGrid');
        } catch (errores){
          console.error('Se ha producido un error al convertir la infomracion del servidor' + errores);
        }
      }, (error) => {
        console.log('Error, no se ha obtenido la informacion');
      }
    );
  }

  ngAfterViewInit(): void {

    // JqGrid
    ( jQuery ('#' + this.jqGridId ) ).jqGrid({
      colNames: this.jqGridColNames,
      colModel: this.jqGridColModel,
      pager: this.jqGridPagerId,
      caption: '',
      rowNum: 10,
      rowList: [10, 20, 50, 100],
      viewrecords: true,
      gridview: true,
      autowidth: false,
      onCellSelect: (rowid: any, iCol: any, cellcontent: any, e: any) => {
        // Si se pulsa sobre la columna 1, pulsan sobre el boton
        console.log('Se ha pulsado sobre el boton ver para ir al usuario con id: ' + iCol);
        if (iCol === 1 )
        {
          // Obtenemos el valor de la columna oculta
          const idCelValue = jQuery( '#' + this.jqGridId ).jqGrid ('getCell', rowid, 'id');
          console.log('Se ha pulsado sobre el boton ver para ir al usuario con id: ' + idCelValue);
          this.router.navigate(['usuarios', 'usuario', idCelValue]);
        }
      }
    });

    // Filtros
    jQuery('#' + this.jqGridId).jqGrid('filterToolbar', {searchOperators : true});

    jQuery('#exportar').on('click', () => {

      console.log('Se inicia la exportacion a excel del listado de Usuarios');

      jQuery('#' + this.jqGridId).tableExport({ type: 'excel', fileName: 'listado-usuarios' , escape: 'false'} );

    });

  }

  ngOnInit(): void {

    this.getListadoUsuarios();
  }

}




