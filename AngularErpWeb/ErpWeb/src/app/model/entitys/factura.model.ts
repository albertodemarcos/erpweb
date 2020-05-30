export interface Factura {
    id: number;
    codigo: string;
    empresaId: number;
    fechaCreacion: Date;
    fechaInicio: Date;
    fechaFin: Date;
    descripcion: string;
    baseImponible: number;
    cuotaTributaria: number;
    importeTotal: number;
}


