export interface Articulo {
    id: number;
    codigo: string;
    empresaId: number;
    nombre: string;
    descripcion: string;
    baseImponible: number;
    impuestoId: number;
    importeTotal: number;
    proveedorId: number;
    almacenId: number;
}
