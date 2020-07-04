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
    this.jqGridColNames = ['ID', 'Ver', 'Código', 'Fecha Compra', 'Artículo', 'Cantidad', 'Base Impl.', 'Impuesto', 'Importe total'];
    this.jqGridColModel = [
      { name: 'id', index: '', hidden: true},
      { name: '', index: '', width: '60', height: '50', align: 'center', search: false, sortable: false, formatter:
        () => {
          return '<button class="btn btn-primary btn-xs" style="margin: 0%; width: 15 px; height: 30px">' +
            '<i class="fa fa-search-minus" aria-hidden="true"></i></button>';
        }
      },
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
      autowidth: false,
      onCellSelect: (rowid: any, iCol: any, cellcontent: any, e: any) => {
        // Si se pulsa sobre la columna 1, pulsan sobre el boton
        console.log('Se ha pulsado sobre el boton ver para ir a la compra con id: ' + iCol);
        if (iCol === 1 )
        {
          // Obtenemos el valor de la columna oculta
          const idCelValue = jQuery( '#' + this.jqGridId ).jqGrid ('getCell', rowid, 'id');
          console.log('Se ha pulsado sobre el boton ver para ir a la compra con id: ' + idCelValue);
          this.router.navigate(['compras', 'compra', idCelValue]);
        }
      }
    });

    // Filtros
    jQuery('#' + this.jqGridId).jqGrid('filterToolbar', {searchOperators : true});

    jQuery('#exportar').on('click', () => {

      console.log('Se inicia la exportacion a excel del listado de compras');

      jQuery('#' + this.jqGridId).tableExport({ type: 'excel', fileName: 'listado-compras' , escape: 'false'} );

    });
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
