import { Articulo } from './articulo.model';
import { Almacen } from './almacen.model';

export class Stock {
    id: number;
    codigo: string;
    almacenId: number;
    articuloId: number;
    almacen: Almacen;
    articulo: Articulo;
    cantidad: number;
}


