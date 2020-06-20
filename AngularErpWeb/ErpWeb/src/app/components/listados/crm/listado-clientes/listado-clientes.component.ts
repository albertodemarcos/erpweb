import { Component, OnInit , AfterViewInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { ClienteService } from 'src/app/services/crm/cliente.service';
import { Cliente } from 'src/app/model/entitys/cliente.model';

declare var jQuery: any;

@Component({
  selector: 'app-listado-clientes',
  templateUrl: './listado-clientes.component.html',
  styleUrls: ['./listado-clientes.component.css']
})
export class ListadoClientesComponent implements OnInit, AfterViewInit {

  public tituloListado: string;
  private jqGridId: string;
  private jqGridPagerId: string;
  private jqGridColNames: string[];
  private jqGridColModel: {};
  private jqGridData: Cliente[];

  constructor(private clienteService: ClienteService, private router: Router, private activatedRoute: ActivatedRoute) {
    this.tituloListado = 'Listado de clientes';
    this.jqGridId = 'clientes-grid';
    this.jqGridPagerId = 'clientes-pager';
    this.jqGridColNames = ['', 'Código', 'Nombre', 'Apellido', 'CIF/NIF', 'Teléfono', 'Tipo cliente'];
    this.jqGridColModel = [
      { name: 'id', index: '', width: '40', search: false, sortable: false },
      { name: 'codigo', index: '', width: '', search: true, sortable: true },
      { name: 'nombre', index: '', width: '', search: true, sortable: true },
      { name: 'apellido', index: '', width: '', search: true, sortable: true },
      { name: 'nif', index: '', width: '', search: true, sortable: true },
      { name: 'telefono', index: '', width: '', search: true, sortable: true },
      { name: 'tipoCliente', index: '', width: '', search: true, sortable: true}
    ];
    this.jqGridData = new Array<Cliente>();
  }

  getListadoClientes(): void{

    console.log('Entramos en el metodo getListadoClientes()');

    this.clienteService.getClientes().then( (clientes) => {
        try {
          // Introducimos los datos
          clientes.forEach(cliente => this.jqGridData.push(cliente));
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

      console.log('Se inicia la exportacion a excel del listado de clientes');

      jQuery('#' + this.jqGridId).tableExport({ type: 'excel', fileName: 'listadoClientes' , escape: 'false'} );

    });

  }



  ngOnInit(): void {
    this.getListadoClientes();
  }


}
