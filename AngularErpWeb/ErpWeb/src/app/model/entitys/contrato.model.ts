
import { LineaContrato } from './linea-contrato.model';

export class Contrato {
    id: number;
    codigo: string;
    fechaCreacion: Date;
    fechaInicio: Date;
    fechaFin: Date;
    fechaInicioTexto: string;
    fechaFinTexto: string;
    articulosCantidadMap: Map<number, number>; // (key-> articuloId, value -> cantidad) Evitamos duplicados
    articulosAlmacenMap: Map<number, number>; // (key-> articuloId, value -> almacenId) Evitamos duplicados
    articulosCantidad: {};
    articulosAlmacen: {};
    lineaContrato: Array<LineaContrato>; // ¿¿??
    articulo: string; // NO se utiliza
    cantidad: number; // NO se utiliza
    baseImponibleTotal: number; // NO se utiliza
    impuesto: string; // NO se utiliza
    importeTotal: number; // NO se utiliza
    descripcion: string;
}

