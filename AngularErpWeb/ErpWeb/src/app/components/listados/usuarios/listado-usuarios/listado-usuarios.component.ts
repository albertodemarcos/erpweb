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
    this.jqGridColNames = ['', 'Código', 'Nombre', 'Apellido', 'CIF/NIF', 'Teléfono', 'Tipo Usuario'];
    this.jqGridColModel = [
      { name: 'id', index: '', width: '40', search: false, sortable: false },
      { name: 'codigo', index: '', width: '', search: true, sortable: true },
      { name: 'nombre', index: '', width: '', search: true, sortable: true },
      { name: 'apellido', index: '', width: '', search: true, sortable: true },
      { name: 'nif', index: '', width: '', search: true, sortable: true },
      { name: 'telefono', index: '', width: '', search: true, sortable: true },
      { name: 'tipoUsuario', index: '', width: '', search: true, sortable: true}
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
      rowList: [10, 20],
      viewrecords: true,
      gridview: true,
      autowidth: true
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




