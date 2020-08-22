
import { LineaCompra } from 'src/app/model/entitys/linea-compra.model';

export class Compra {
    id: number;
    codigo: string;
    fechaCompra: Date;
    fechaCompraTexto: string;
    articulosCantidadMap: Map<number, number>; // (key-> articuloId, value -> cantidad) Evitamos duplicados
    articulosCantidad: {};
    lineaCompra: LineaCompra[]; // ¿¿??
    articulo: string; // NO se utiliza
    cantidad: number; // NO se utiliza
    baseImponibleTotal: number; // NO se utiliza
    impuesto: string; // NO se utiliza
    importeTotal: number; // NO se utiliza
}
