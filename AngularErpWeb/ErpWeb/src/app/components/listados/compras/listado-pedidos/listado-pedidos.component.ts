import { Component, OnInit , AfterViewInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { PedidoService } from 'src/app/services/compras/pedido.service';
import { Pedido } from 'src/app/model/entitys/pedido.model';

declare var jQuery: any;

@Component({
  selector: 'app-listado-pedidos',
  templateUrl: './listado-pedidos.component.html',
  styleUrls: ['./listado-pedidos.component.css']
})
export class ListadoPedidosComponent implements OnInit, AfterViewInit {

  public tituloListado: string;
  private jqGridId: string;
  private jqGridPagerId: string;
  private jqGridColNames: string[];
  private jqGridColModel: {};
  private jqGridData: Pedido[];

  constructor(private pedidoService: PedidoService, private router: Router, private activatedRoute: ActivatedRoute) {
    this.tituloListado = 'Listado de pedidos';
    this.jqGridId = 'pedidos-grid';
    this.jqGridPagerId = 'pedidos-pager';
    this.jqGridColNames = ['', 'Código', 'Fecha Pedido', 'Artículo', 'Cantidad', 'Base Impl.', 'Impuesto', 'Importe total'];
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
    this.jqGridData = new Array<Pedido>();
  }

  getListadoPedidos(): void{

    console.log('Entramos en el metodo getListadoPedidos()');

    this.pedidoService.getPedidos().then( (pedidos) => {
        try {
          // Introducimos los datos
          pedidos.forEach(pedido => this.jqGridData.push(pedido));
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

  }

  ngOnInit(): void {
    this.getListadoPedidos();
  }

}
