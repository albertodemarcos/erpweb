
import { LineaPedido } from 'src/app/model/entitys/linea-pedido.model';

export class Pedido {
    id: number;
    codigo: string;
    fechaPedido: Date;
    fechaPedidoTexto: string;
    articulosCantidadMap: Map<number, number>; // (key-> articuloId, value -> cantidad) Evitamos duplicados
    articulosCantidad: {};
    lineaPedido: Array<LineaPedido>; // ¿¿??
    articulo: string; // NO se utiliza
    cantidad: number; // NO se utiliza
    baseImponibleTotal: number; // NO se utiliza
    impuesto: string; // NO se utiliza
    importeTotal: number; // NO se utiliza
}
