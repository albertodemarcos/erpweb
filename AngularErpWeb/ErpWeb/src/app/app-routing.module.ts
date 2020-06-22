import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import {HomeComponent} from 'src/app/components/home/home.component';

import { PlanificadorComponent } from './components/calendario/planificador/planificador.component';


// LISTADOS
import { ListadoClientesComponent } from './components/listados/crm/listado-clientes/listado-clientes.component';
import { ListadoContratosComponent } from './components/listados/ventas/listado-contratos/listado-contratos.component';
import { ListadoVentasComponent } from './components/listados/ventas/listado-ventas/listado-ventas.component';
import { ListadoFacturasComponent } from './components/listados/ventas/listado-facturas/listado-facturas.component';
import { ListadoComprasComponent } from './components/listados/compras/listado-compras/listado-compras.component';
import { ListadoPedidosComponent } from './components/listados/compras/listado-pedidos/listado-pedidos.component';
import { ListadoProveedoresComponent } from './components/listados/compras/listado-proveedores/listado-proveedores.component';
import { ListadoArticulosComponent } from './components/listados/inventario/listado-articulos/listado-articulos.component';
import { ListadoAlmacenComponent } from './components/listados/inventario/listado-almacen/listado-almacen.component';
import { ListadoVehiculosComponent } from './components/listados/inventario/listado-vehiculos/listado-vehiculos.component';
import { ListadoEmpleadosComponent } from './components/listados/empresa/listado-empleados/listado-empleados.component';

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

import { ClienteComponent } from './components/cards/crm/cliente/cliente.component';

const routes: Routes = [
  // RUTA INICIO
  { path: 'inicio', component: HomeComponent },
  // RUTAS CRM
  { path: 'clientes', component: ListadoClientesComponent },
  { path: 'clientes/nuevo-cliente', component: FormularioClienteComponent },
  { path: 'clientes/cliente/:id', component: ClienteComponent, data: {cliente: 'cliente'} },
  // RUTAS PLANIFICADOR
  { path: 'planificador/calendario', component: PlanificadorComponent },
  // RUTAS VENTAS
  { path: 'contratos', component: ListadoContratosComponent },
  { path: 'contratos/nuevo-contrato', component: FormularioContratoComponent },
  { path: 'contratos/contrato/:id', component: ClienteComponent, data: {cliente: 'cliente'} },
  { path: 'ventas', component: ListadoVentasComponent },
  { path: 'ventas/nueva-venta', component: FormularioVentaComponent },
  { path: 'ventas/venta/:id', component: ClienteComponent, data: {cliente: 'cliente'} },
  { path: 'facturas', component: ListadoFacturasComponent },
  { path: 'facturas/nuevo-factura', component: FormularioFacturaComponent },
  { path: 'facturas/factura/:id', component: ClienteComponent, data: {cliente: 'cliente'} },
  // RUTAS COMPRAS
  { path: 'compras', component: ListadoComprasComponent },
  { path: 'compras/nueva-compra', component: FormularioCompraComponent },
  { path: 'compras/compra/:id', component: ClienteComponent, data: {cliente: 'cliente'} },
  { path: 'pedidos', component: ListadoPedidosComponent },
  { path: 'pedidos/nuevo-pedido', component: FormularioPedidoComponent },
  { path: 'pedidos/pedido/:id', component: ClienteComponent, data: {cliente: 'cliente'} },
  { path: 'proveedor', component: ListadoProveedoresComponent },
  { path: 'proveedor/nuevo-proveedor', component: FormularioProveedorComponent },
  { path: 'proveedor/proveedor/:id', component: ClienteComponent, data: {cliente: 'cliente'} },
  // RUTAS INVENTARIO
  { path: 'catalgo/articulos', component: ListadoArticulosComponent },
  { path: 'catalgo/articulos/nuevo-articulo', component: FormularioArticuloComponent },
  { path: 'catalgo/articulo/:id', component: ClienteComponent, data: {cliente: 'cliente'} },
  { path: 'almacenes', component: ListadoAlmacenComponent },
  { path: 'almacenes/nuevo-almacen', component: FormularioAlmacenComponent },
  { path: 'almacenes/almacen/:id', component: ClienteComponent, data: {cliente: 'cliente'} },
  { path: 'vehiculos', component: ListadoVehiculosComponent },
  { path: 'vehiculos/nuevo-vehiculo', component: FormularioVehiculoComponent },
  { path: 'vehiculos/vehiculo/:id', component: ClienteComponent, data: {cliente: 'cliente'} },
  // RUTAS EMPRESA
  { path: 'empresa', component: FormularioClienteComponent }, // CAMBIAR
  { path: 'rrhh', component: ListadoEmpleadosComponent },
  { path: 'rrhh/nuevo-empleado', component: FormularioEmpleadoComponent },
  { path: 'rrhh/empleado/:id', component: ClienteComponent, data: {cliente: 'cliente'} },
  // RUTAS PERFIL
  { path: 'perfil', component: FormularioClienteComponent }, // CAMBIAR
  { path: 'salir', component: FormularioClienteComponent }, // CAMBIAR
  { path: '**', redirectTo: 'inicio', pathMatch: 'full'}, // Ruta general
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }


/* RUTAS FUTURAS */
// { path: 'clientes/buscar-clientes', component: FormularioClienteComponent }, // CAMBIAR
// { path: 'ingresos/buscar-ingreso', component: FormularioClienteComponent }, // CAMBIAR
// { path: 'gastos/buscar-gasto', component: FormularioClienteComponent }, // CAMBIAR
// { path: 'contratos/buscar-contrato', component: FormularioClienteComponent }, // CAMBIAR
// { path: 'ventas/buscar-venta', component: FormularioClienteComponent }, // CAMBIAR
// { path: 'facturas/buscar-factura', component: FormularioClienteComponent }, // CAMBIAR
// { path: 'compras/buscar-compra', component: FormularioClienteComponent }, // CAMBIAR
// { path: 'pedidos/buscar-pedido', component: FormularioClienteComponent }, // CAMBIAR
// { path: 'proveedor/buscar-proveedor', component: FormularioClienteComponent }, // CAMBIAR
// { path: 'catalgo/articulos/buscar-articulo', component: FormularioClienteComponent }, // CAMBIAR
// { path: 'almacenes/buscar-almacen', component: FormularioClienteComponent }, // CAMBIAR
// { path: 'vehiculos/buscar-vehiculo', component: FormularioClienteComponent }, // CAMBIAR
// { path: 'rrhh/buscar-empleado', component: FormularioClienteComponent }, // CAMBIAR
