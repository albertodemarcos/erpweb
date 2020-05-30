export interface Gasto {
    id: number;
    codigo: string;
    empresaId: number;
    procendecia: string;
    baseImponible: number;
    cuotaTributaria: number;
    importeTotal: number;
    descripcion: string;
    observaciones: string;
}
