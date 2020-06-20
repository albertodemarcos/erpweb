import { Component, OnInit , AfterViewInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ProveedorService } from 'src/app/services/compras/proveedor.service';
import { Proveedor } from 'src/app/model/entitys/proveedor.model';

declare var jQuery: any;

@Component({
  selector: 'app-listado-proveedores',
  templateUrl: './listado-proveedores.component.html',
  styleUrls: ['./listado-proveedores.component.css']
})
export class ListadoProveedoresComponent implements OnInit, AfterViewInit {

  public tituloListado: string;
  private jqGridId: string;
  private jqGridPagerId: string;
  private jqGridColNames: string[];
  private jqGridColModel: {};
  private jqGridData: Proveedor[];

  constructor(private proveedorService: ProveedorService, private router: Router, private activatedRoute: ActivatedRoute) {
    this.tituloListado = 'Listado de proveedor';
    this.jqGridId = 'proveedores-grid';
    this.jqGridPagerId = 'proveedores-pager';
    this.jqGridColNames = ['', 'Código', 'Nombre', 'Empresa', 'Teléfono', 'Tipo proveedor'];
    this.jqGridColModel = [
      { name: 'id', index: '', width: '40', search: false, sortable: false },
      { name: 'codigo', index: '', width: '', search: true, sortable: true },
      { name: 'nombre', index: '', width: '', search: true, sortable: true },
      { name: 'nombreEmpresa', index: '', width: '', search: true, sortable: true },
      { name: 'telefono', index: '', width: '', search: true, sortable: true },
      { name: 'tipoProveedor', index: '', width: '', search: true, sortable: true}
    ];
    this.jqGridData = new Array<Proveedor>();
  }

  getListadoProveedores(): void{

    console.log('Entramos en el metodo getListadoProveedors()');

    this.proveedorService.getProveedores().then( (proveedores) => {
        try {
          // Introducimos los datos
          proveedores.forEach(proveedor => this.jqGridData.push(proveedor));
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

      console.log('Se inicia la exportacion a excel del listado de proveedores');

      jQuery('#' + this.jqGridId).tableExport({ type: 'excel', fileName: 'listado-proveedores' , escape: 'false'} );

    });

  }

  ngOnInit(): void {
    this.getListadoProveedores();
  }




}
