export class Pedido {
    id: number;
    codigo: string;
    fechaPedido: Date;
    articulo: string;
    cantidad: number;
    baseImponibleTotal: number;
    impuesto: string;
    importeTotal: number;
}