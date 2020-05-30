export interface Venta {
    id: number;
    codigo: string;
    empresaId: number;
    fechaCreacion: Date;
    fechaInicio: Date;
    fechaFin: Date;
    descripcion: string;
    baseImponibleTotal: number;
    importeTotal: number;
}