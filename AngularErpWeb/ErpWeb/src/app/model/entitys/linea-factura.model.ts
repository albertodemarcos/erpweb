
import { Articulo } from './articulo.model';

export class LineaFactura {
    id: number;
    compraId: number;
    articuloId: number;
    articuloDto: Articulo;
    baseImponible: number;
    importeImpuesto: number;
    importeTotal: number;
    cantidad: number;
    almacenId: number;
}
