import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


/*** SERVICIOS */

import { AutenticacionService } from './services/autenticacion/autenticacion.service';

/** COMPONENTES */

// IMPORTANTES
import {HomeComponent} from 'src/app/components/home/home.component';
import { PlanificadorComponent } from './components/calendario/planificador/planificador.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';

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
import { ListadoStockComponent } from './components/listados/inventario/listado-stock/listado-stock.component';

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
import { FormularioEmpresaComponent } from './components/formularios/empresa/formulario-empresa/formulario-empresa.component';
import { FormularioStockComponent } from './components/formularios/inventario/formulario-stock/formulario-stock.component';

// CARDS
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
import { StockComponent } from './components/cards/inventario/stock/stock.component';

import { Jqgrid5Component } from './components/jqgrid5/jqgrid5.component';
// canActiva -> meter en los sitios que se hacen peticiones

const routes: Routes = [
  // RUTA INICIO
  { path: 'inicio', component: HomeComponent, canActivate: [AutenticacionService] },
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent, canActivate: [AutenticacionService] },
  // RUTAS CRM
  { path: 'clientes', component: ListadoClientesComponent, canActivate: [AutenticacionService] },
  { path: 'clientes/nuevo-cliente', component: FormularioClienteComponent, canActivate: [AutenticacionService] },
  { path: 'clientes/editar-cliente/:id', component: FormularioClienteComponent, canActivate: [AutenticacionService] },
  { path: 'clientes/cliente/:id', component: ClienteComponent, data: {cliente: 'cliente'}, canActivate: [AutenticacionService] },
  // RUTAS PLANIFICADOR
  { path: 'planificador/calendario', component: PlanificadorComponent, canActivate: [AutenticacionService] },
  { path: 'planificador/jqgrid5', component: Jqgrid5Component },
  // RUTAS VENTAS
  { path: 'contratos', component: ListadoContratosComponent, canActivate: [AutenticacionService] },
  { path: 'contratos/nuevo-contrato', component: FormularioContratoComponent, canActivate: [AutenticacionService] },
  { path: 'contratos/editar-contrato/:id', component: FormularioContratoComponent, canActivate: [AutenticacionService] },
  { path: 'contratos/contrato/:id', component: ContratoComponent, data: {contrato: 'contrato'}, canActivate: [AutenticacionService] },
  { path: 'ventas', component: ListadoVentasComponent, canActivate: [AutenticacionService] },
  { path: 'ventas/nueva-venta', component: FormularioVentaComponent, canActivate: [AutenticacionService] },
  { path: 'ventas/editar-venta/:id', component: FormularioVentaComponent, canActivate: [AutenticacionService] },
  { path: 'ventas/venta/:id', component: VentaComponent, data: {venta: 'venta'}, canActivate: [AutenticacionService] },
  { path: 'facturas', component: ListadoFacturasComponent, canActivate: [AutenticacionService] },
  { path: 'facturas/nuevo-factura', component: FormularioFacturaComponent, canActivate: [AutenticacionService] },
  { path: 'facturas/editar-factura/:id', component: FormularioFacturaComponent, canActivate: [AutenticacionService] },
  { path: 'facturas/factura/:id', component: FacturaComponent, data: {factura: 'factura'}, canActivate: [AutenticacionService] },
  // RUTAS COMPRAS
  { path: 'compras', component: ListadoComprasComponent, canActivate: [AutenticacionService] },
  { path: 'compras/nueva-compra', component: FormularioCompraComponent, canActivate: [AutenticacionService] },
  { path: 'compras/editar-compra/:id', component: FormularioCompraComponent, canActivate: [AutenticacionService] },
  { path: 'compras/compra/:id', component: CompraComponent, data: {compra: 'compra'}, canActivate: [AutenticacionService] },
  { path: 'pedidos', component: ListadoPedidosComponent, canActivate: [AutenticacionService] },
  { path: 'pedidos/nuevo-pedido', component: FormularioPedidoComponent, canActivate: [AutenticacionService] },
  { path: 'pedidos/editar-pedido/:id', component: FormularioPedidoComponent, canActivate: [AutenticacionService] },
  { path: 'pedidos/pedido/:id', component: PedidoComponent, data: {pedido: 'pedido'}, canActivate: [AutenticacionService] },
  { path: 'proveedores', component: ListadoProveedoresComponent, canActivate: [AutenticacionService] },
  { path: 'proveedores/nuevo-proveedor', component: FormularioProveedorComponent, canActivate: [AutenticacionService] },
  { path: 'proveedores/editar-proveedor/:id', component: FormularioProveedorComponent, canActivate: [AutenticacionService] },
  { path: 'proveedores/proveedor/:id', component: ProveedorComponent, data: {proveedor: 'proveedor'}, canActivate: [AutenticacionService] },
  // RUTAS INVENTARIO
  { path: 'catalago/articulos', component: ListadoArticulosComponent, canActivate: [AutenticacionService] },
  { path: 'catalago/articulos/nuevo-articulo', component: FormularioArticuloComponent, canActivate: [AutenticacionService] },
  { path: 'catalago/editar-articulo/:id', component: FormularioArticuloComponent, canActivate: [AutenticacionService] },
  { path: 'catalago/articulo/:id', component: ArticuloComponent, data: {articulo: 'articulo'}, canActivate: [AutenticacionService] },
  { path: 'almacenes', component: ListadoAlmacenComponent, canActivate: [AutenticacionService] },
  { path: 'almacenes/nuevo-almacen', component: FormularioAlmacenComponent, canActivate: [AutenticacionService] },
  { path: 'almacenes/editar-almacen/:id', component: FormularioAlmacenComponent, canActivate: [AutenticacionService] },
  { path: 'almacenes/almacen/:id', component: AlmacenComponent, data: {almacen: 'almacen'}, canActivate: [AutenticacionService] },
// New
  { path: 'stock', component: ListadoStockComponent, canActivate: [AutenticacionService] },
  { path: 'stock/nuevo-stock', component: FormularioStockComponent, canActivate: [AutenticacionService] },
  { path: 'stock/editar-stock/:id', component: FormularioStockComponent, canActivate: [AutenticacionService] },
  { path: 'stock/almacen/:id', component: StockComponent, data: {stock: 'stock'}, canActivate: [AutenticacionService] },
// Fin new
  { path: 'vehiculos', component: ListadoVehiculosComponent, canActivate: [AutenticacionService] },
  { path: 'vehiculos/nuevo-vehiculo', component: FormularioVehiculoComponent, canActivate: [AutenticacionService] },
  { path: 'vehiculos/editar-vehiculo/:id', component: FormularioVehiculoComponent, canActivate: [AutenticacionService] },
  { path: 'vehiculos/vehiculo/:id', component: VehiculoComponent, data: {vehiculo: 'vehiculo'}, canActivate: [AutenticacionService] },
  // RUTAS EMPRESA
  { path: 'empresa/:id', component: EmpresaComponent, canActivate: [AutenticacionService] },
  { path: 'empresa/editar-empresa/:id', component: FormularioEmpresaComponent, canActivate: [AutenticacionService] },
  { path: 'rrhh', component: ListadoEmpleadosComponent, canActivate: [AutenticacionService] },
  { path: 'rrhh/nuevo-empleado', component: FormularioEmpleadoComponent, canActivate: [AutenticacionService] },
  { path: 'rrhh/editar-empleado/:id', component: FormularioEmpleadoComponent, canActivate: [AutenticacionService] },
  { path: 'rrhh/empleado/:id', component: EmpleadoComponent, data: {empleado: 'empleado'}, canActivate: [AutenticacionService] },
  // RUTAS ADMINISTRACION
  { path: 'usuarios', component: ListadoUsuariosComponent, canActivate: [AutenticacionService] },
  { path: 'usuarios/nuevo-usuario', component: FormularioUsuarioComponent, canActivate: [AutenticacionService] },
  { path: 'usuarios/editar-usuario/:id', component: FormularioUsuarioComponent, canActivate: [AutenticacionService] },
  { path: 'usuarios/usuario/:id', component: UsuarioComponent, data: {empleado: 'empleado'}, canActivate: [AutenticacionService] },
  // RUTAS PERFIL
  { path: 'perfil', component: UsuarioComponent, canActivate: [AutenticacionService] },
  { path: 'salir', component: LogoutComponent, canActivate: [AutenticacionService] },
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
