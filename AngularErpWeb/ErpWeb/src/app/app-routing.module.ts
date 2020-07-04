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
import { ListadoUsuariosComponent } from './components/listados/usuarios/listado-usuarios/listado-usuarios.component';

// FORMULARIOS
import { FormularioClienteComponent } from './components/formularios/cllientes/formulario-cliente/formulario-cliente.component';
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
import { FormularioUsuarioComponent } from './components/formularios/usuarios/formulario-usuario/formulario-usuario.component';

import { ClienteComponent } from './components/cards/crm/cliente/cliente.component';
import { ContratoComponent } from './components/cards/ventas/contrato/contrato.component';
import { VentaComponent } from './components/cards/ventas/venta/venta.component';
import { FacturaComponent } from './components/cards/ventas/factura/factura.component';
import { CompraComponent } from './components/cards/compras/compra/compra.component';
import { PedidoComponent } from './components/cards/compras/pedido/pedido.component';
import { ProveedorComponent } from './components/cards/compras/proveedor/proveedor.component';
import { AlmacenComponent } from './components/cards/inventario/almacen/almacen.component';
import { ArticuloComponent } from './components/cards/inventario/articulo/articulo.component';
import { VehiculoComponent } from './components/cards/inventario/vehiculo/vehiculo.component';
import { EmpleadoComponent } from './components/cards/empresas/empleado/empleado.component';
import { EmpresaComponent } from './components/cards/empresas/empresa/empresa.component';
import { UsuarioComponent } from './components/cards/usuarios/usuario/usuario.component';

const routes: Routes = [
  // RUTA INICIO
  { path: 'inicio', component: HomeComponent },
  // RUTAS CRM
  { path: 'clientes', component: ListadoClientesComponent },
  { path: 'clientes/nuevo-cliente', component: FormularioClienteComponent },
  { path: 'clientes/editar-cliente/:id', component: FormularioClienteComponent },
  { path: 'clientes/cliente/:id', component: ClienteComponent, data: {cliente: 'cliente'} },
  // RUTAS PLANIFICADOR
  { path: 'planificador/calendario', component: PlanificadorComponent },
  // RUTAS VENTAS
  { path: 'contratos', component: ListadoContratosComponent },
  { path: 'contratos/nuevo-contrato', component: FormularioContratoComponent },
  { path: 'contratos/editar-contrato/:id', component: FormularioContratoComponent },
  { path: 'contratos/contrato/:id', component: ContratoComponent, data: {contrato: 'contrato'} },
  { path: 'ventas', component: ListadoVentasComponent },
  { path: 'ventas/nueva-venta', component: FormularioVentaComponent },
  { path: 'ventas/editar-venta/:id', component: FormularioVentaComponent },
  { path: 'ventas/venta/:id', component: VentaComponent, data: {venta: 'venta'} },
  { path: 'facturas', component: ListadoFacturasComponent },
  { path: 'facturas/nuevo-factura', component: FormularioFacturaComponent },
  { path: 'facturas/editar-factura/:id', component: FormularioFacturaComponent },
  { path: 'facturas/factura/:id', component: FacturaComponent, data: {factura: 'factura'} },
  // RUTAS COMPRAS
  { path: 'compras', component: ListadoComprasComponent },
  { path: 'compras/nueva-compra', component: FormularioCompraComponent },
  { path: 'compras/editar-compra/:id', component: FormularioCompraComponent },
  { path: 'compras/compra/:id', component: CompraComponent, data: {compra: 'compra'} },
  { path: 'pedidos', component: ListadoPedidosComponent },
  { path: 'pedidos/nuevo-pedido', component: FormularioPedidoComponent },
  { path: 'pedidos/editar-pedido/:id', component: FormularioPedidoComponent },
  { path: 'pedidos/pedido/:id', component: PedidoComponent, data: {pedido: 'pedido'} },
  { path: 'proveedores', component: ListadoProveedoresComponent },
  { path: 'proveedores/nuevo-proveedor', component: FormularioProveedorComponent },
  { path: 'proveedores/editar-proveedor/:id', component: FormularioProveedorComponent },
  { path: 'proveedores/proveedor/:id', component: ProveedorComponent, data: {proveedor: 'proveedor'} },
  // RUTAS INVENTARIO
  { path: 'catalago/articulos', component: ListadoArticulosComponent },
  { path: 'catalago/articulos/nuevo-articulo', component: FormularioArticuloComponent },
  { path: 'catalago/editar-articulo/:id', component: FormularioArticuloComponent },
  { path: 'catalago/articulo/:id', component: ArticuloComponent, data: {articulo: 'articulo'} },
  { path: 'almacenes', component: ListadoAlmacenComponent },
  { path: 'almacenes/nuevo-almacen', component: FormularioAlmacenComponent },
  { path: 'almacenes/editar-almacen/:id', component: FormularioAlmacenComponent },
  { path: 'almacenes/almacen/:id', component: AlmacenComponent, data: {almacen: 'almacen'} },
  { path: 'vehiculos', component: ListadoVehiculosComponent },
  { path: 'vehiculos/nuevo-vehiculo', component: FormularioVehiculoComponent },
  { path: 'vehiculos/editar-vehiculo/:id', component: FormularioVehiculoComponent },
  { path: 'vehiculos/vehiculo/:id', component: VehiculoComponent, data: {vehiculo: 'vehiculo'} },
  // RUTAS EMPRESA
  { path: 'empresa/:id', component: EmpresaComponent },
  { path: 'rrhh', component: ListadoEmpleadosComponent },
  { path: 'rrhh/nuevo-empleado', component: FormularioEmpleadoComponent },
  { path: 'rrhh/editar-empleado/:id', component: FormularioEmpleadoComponent },
  { path: 'rrhh/empleado/:id', component: EmpleadoComponent, data: {empleado: 'empleado'} },
  // RUTAS ADMINISTRACION
  { path: 'usuarios', component: ListadoUsuariosComponent },
  { path: 'usuarios/nuevo-usuario', component: FormularioUsuarioComponent },
  { path: 'usuarios/editar-usuario/:id', component: FormularioUsuarioComponent },
  { path: 'usuarios/usuario/:id', component: UsuarioComponent, data: {empleado: 'empleado'} },
  // RUTAS PERFIL
  { path: 'perfil', component: UsuarioComponent },
  { path: 'salir', component: FormularioClienteComponent },
  // RUTA GENERAL
  { path: '**', redirectTo: 'inicio', pathMatch: 'full'},
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
