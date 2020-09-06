
import { LineaFactura } from './linea-factura.model';

export class Factura {
    id: number;
    codigo: string;
    fechaCreacion: Date;
    fechaInicio: Date;
    fechaFin: Date;
    lineaFactura: Array<LineaFactura>; // ¿¿??
    descripcion: string;
    baseImponible: number;
    impuesto: string;
    importeTotal: number;
}


