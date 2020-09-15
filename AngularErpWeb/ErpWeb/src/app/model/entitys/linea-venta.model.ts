
import { Articulo } from './articulo.model';

export class LineaVenta {
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
