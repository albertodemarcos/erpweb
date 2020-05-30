export interface Contrato {
    id: number;
    codigo: string;
    empresaId: number;
    contratoId: number;
    fechaInicio: Date;
    fechaFin: Date;
    descripcion: string;
    baseImponibleTotal: number;
    importeTotal: number;
}