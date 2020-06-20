import { Component, OnInit , AfterViewInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AlmacenService } from 'src/app/services/inventario/almacen.service';
import { Almacen } from 'src/app/model/entitys/almacen.model';

declare var jQuery: any;

@Component({
  selector: 'app-listado-almacen',
  templateUrl: './listado-almacen.component.html',
  styleUrls: ['./listado-almacen.component.css']
})
export class ListadoAlmacenComponent implements OnInit, AfterViewInit {

  public tituloListado: string;
  private jqGridId: string;
  private jqGridPagerId: string;
  private jqGridColNames: string[];
  private jqGridColModel: {};
  private jqGridData: Almacen[];

  constructor(private clienteService: AlmacenService, private router: Router, private activatedRoute: ActivatedRoute) {
    this.tituloListado = 'Listado de almcenes';
    this.jqGridId = 'almcenes-grid';
    this.jqGridPagerId = 'almcenes-pager';
    this.jqGridColNames = ['', 'Código', 'Nombre', 'Dirección', 'Población', 'Región' ];
    this.jqGridColModel = [
      { name: 'id', index: '', width: '40', search: false, sortable: false },
      { name: 'codigo', index: '', width: '', search: true, sortable: true },
      { name: 'nombre', index: '', width: '', search: true, sortable: true },
      { name: 'direccion', index: '', width: '', search: true, sortable: true },
      { name: 'poblacion', index: '', width: '', search: true, sortable: true },
      { name: 'region', index: '', width: '', search: true, sortable: true }
    ];
    this.jqGridData = new Array<Almacen>();
  }

  getListadoClientes(): void{

    /*console.log('Entramos en el metodo getListadoClientes()');

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
    );*/
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

  }

  ngOnInit(): void {
    this.getListadoClientes();
  }

 


}
