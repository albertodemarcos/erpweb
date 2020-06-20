import { Component, OnInit, AfterViewInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { CompraService } from 'src/app/services/compras/compra.service';
import { Compra } from 'src/app/model/entitys/compra.model';

declare var jQuery: any;

@Component({
  selector: 'app-listado-compras',
  templateUrl: './listado-compras.component.html',
  styleUrls: ['./listado-compras.component.css']
})
export class ListadoComprasComponent implements OnInit, AfterViewInit {

  public tituloListado: string;
  private jqGridId: string;
  private jqGridPagerId: string;
  private jqGridColNames: string[];
  private jqGridColModel: {};
  private jqGridData: Compra[];

  constructor(private compraService: CompraService, private router: Router, private activatedRoute: ActivatedRoute) {
    this.tituloListado = 'Listado de compras';
    this.jqGridId = 'compras-grid';
    this.jqGridPagerId = 'compras-pager';
    this.jqGridColNames = ['', 'Código', 'Fecha Compra', 'Artículo', 'Cantidad', 'Base Impl.', 'Impuesto', 'Importe total'];
    this.jqGridColModel = [
      { name: 'id', index: '', width: '40', search: false, sortable: false },
      { name: 'codigo', index: '', width: '', search: true, sortable: true },
      { name: 'fechaPedido', index: '', width: '', search: true, sortable: true },
      { name: 'articulo', index: '', width: '', search: true, sortable: true },
      { name: 'cantidad', index: '', width: '', search: true, sortable: true },
      { name: 'baseImponibleTotal', index: '', width: '', search: true, sortable: true },
      { name: 'impuesto', index: '', width: '', search: true, sortable: true},
      { name: 'importeTotal', index: '', width: '', search: true, sortable: true}
    ];
    this.jqGridData = new Array<Compra>();
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

  getListadoCompras(): void{

    console.log('Entramos en el metodo getListadoCompras()');

    this.compraService.getCompras().then( (compras) => {
        try {
          // Introducimos los datos
          compras.forEach(compra => this.jqGridData.push(compra));
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


  ngOnInit(): void {
  }

}
