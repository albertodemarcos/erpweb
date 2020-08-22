
import { LineaVenta } from 'src/app/model/entitys/linea-venta.model';

export class Venta {
    id: number;
    codigo: string;
    fechaCreacion: Date;
    fechaInicio: Date;
    fechaFin: Date;
    fechaInicioTexto: string;
    fechaFinTexto: string;
    articulosCantidadMap: Map<number, number>; // (key-> articuloId, value -> cantidad) Evitamos duplicados
    articulosCantidad: {};
    lineaVenta: LineaVenta[]; // ¿¿??
    articulo: string; // NO se utiliza
    cantidad: number; // NO se utiliza
    baseImponibleTotal: number; // NO se utiliza
    impuesto: string; // NO se utiliza
    importeTotal: number; // NO se utiliza
    descripcion: string;
}

