import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import {HomeComponent} from 'src/app/components/home/home.component';

// FORMULARIOS
import { FormularioClienteComponent } from './components/formularios/cllientes/formulario-cliente/formulario-cliente.component';
import { FormularioGastoComponent } from './components/formularios/bi/formulario-gasto/formulario-gasto.component';
import { FormularioIngresoComponent } from './components/formularios/bi/formulario-ingreso/formulario-ingreso.component';
import { FormularioCompraComponent } from './components/formularios/compras/formulario-compra/formulario-compra.component';
import { FormularioPedidoComponent } from './components/formularios/compras/formulario-pedido/formulario-pedido.component';
import { FormularioProveedorComponent } from './components/formularios/compras/formulario-proveedor/formulario-proveedor.component';
import { FormularioEmpleadoComponent } from './components/formularios/empresa/formulario-empleado/formulario-empleado.component';
import { FormularioAlmacenComponent } from './components/formularios/inventario/formulario-almacen/formulario-almacen.component';
import { FormularioArticuloComponent } from './components/formularios/inventario/formulario-articulo/formulario-articulo.component';
import { FormularioVehiculoComponent } from './components/formularios/inventario/formulario-vehiculo/formulario-vehiculo.component';
import { FormularioVentaComponent } from './components/formularios/ventas/formulario-venta/formulario-venta.component';
import { FormularioContratoComponent } from './components/formularios/ventas/formulario-contrato/formulario-contrato.component';
import { FormularioFacturaComponent } from './components/formularios/ventas/formulario-factura/formulario-factura.component';

const routes: Routes = [
  // RUTA INICIO
  { path: 'inicio', component: HomeComponent },
  // RUTAS CRM
  { path: 'clientes', component: FormularioClienteComponent }, // CAMBIAR
  { path: 'clientes/nuevo-cliente', component: FormularioClienteComponent },
  { path: 'clientes/buscar-clientes', component: FormularioClienteComponent }, // CAMBIAR
  // RUTAS BI
  { path: 'ingresos', component: FormularioClienteComponent }, // CAMBIAR
  { path: 'ingresos/nuevo-ingreso', component: FormularioIngresoComponent },
  { path: 'ingresos/buscar-ingreso', component: FormularioClienteComponent }, // CAMBIAR
  { path: 'gastos', component: FormularioClienteComponent }, // CAMBIAR
  { path: 'gastos/nuevo-gasto', component: FormularioGastoComponent },
  { path: 'gastos/buscar-gasto', component: FormularioClienteComponent }, // CAMBIAR
  { path: 'resultados', component: FormularioClienteComponent }, // CAMBIAR
  // RUTAS VENTAS
  { path: 'contratos', component: FormularioClienteComponent }, // CAMBIAR
  { path: 'contratos/nuevo-contrato', component: FormularioContratoComponent },
  { path: 'contratos/buscar-contrato', component: FormularioClienteComponent }, // CAMBIAR
  { path: 'ventas', component: FormularioClienteComponent }, // CAMBIAR
  { path: 'ventas/nueva-venta', component: FormularioVentaComponent },
  { path: 'ventas/buscar-venta', component: FormularioClienteComponent }, // CAMBIAR
  { path: 'facturas', component: FormularioClienteComponent }, // CAMBIAR
  { path: 'facturas/nuevo-factura', component: FormularioFacturaComponent },
  { path: 'facturas/buscar-factura', component: FormularioClienteComponent }, // CAMBIAR
  // RUTAS COMPRAS
  { path: 'compras', component: FormularioClienteComponent }, // CAMBIAR
  { path: 'compras/nueva-compra', component: FormularioCompraComponent },
  { path: 'compras/buscar-compra', component: FormularioClienteComponent }, // CAMBIAR
  { path: 'pedidos', component: FormularioClienteComponent }, // CAMBIAR
  { path: 'pedidos/nuevo-pedido', component: FormularioPedidoComponent },
  { path: 'pedidos/buscar-pedido', component: FormularioClienteComponent }, // CAMBIAR
  { path: 'proveedor', component: FormularioClienteComponent }, // CAMBIAR
  { path: 'proveedor/nuevo-proveedor', component: FormularioProveedorComponent },
  { path: 'proveedor/buscar-proveedor', component: FormularioClienteComponent }, // CAMBIAR
  // RUTAS INVENTARIO
  { path: 'catalgo/articulos', component: FormularioClienteComponent }, // CAMBIAR
  { path: 'catalgo/articulos/nuevo-articulo', component: FormularioArticuloComponent },
  { path: 'catalgo/articulos/buscar-articulo', component: FormularioClienteComponent }, // CAMBIAR
  { path: 'almacenes', component: FormularioClienteComponent }, // CAMBIAR
  { path: 'almacenes/nuevo-almacen', component: FormularioAlmacenComponent },
  { path: 'almacenes/buscar-almacen', component: FormularioClienteComponent }, // CAMBIAR
  { path: 'vehiculos', component: FormularioClienteComponent }, // CAMBIAR
  { path: 'vehiculos/nuevo-vehiculo', component: FormularioVehiculoComponent },
  { path: 'vehiculos/buscar-vehiculo', component: FormularioClienteComponent }, // CAMBIAR
  // RUTAS EMPRESA
  { path: 'empresa', component: FormularioClienteComponent }, // CAMBIAR
  { path: 'rrhh', component: FormularioClienteComponent }, // CAMBIAR
  { path: 'rrhh/nuevo-empleado', component: FormularioEmpleadoComponent },
  { path: 'rrhh/buscar-empleado', component: FormularioClienteComponent }, // CAMBIAR
  // RUTAS PERFIL
  { path: 'perfil', component: FormularioClienteComponent }, // CAMBIAR
  { path: 'salir', component: FormularioClienteComponent }, // CAMBIAR

  { path: '**', redirectTo: 'inicio', pathMatch: 'full'}, // Ruta general
];

console.log('RUTAS DEFINIDAS: ' + routes.values);

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
