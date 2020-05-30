export interface LineaCompra {
    id: number;
    codigo: string;
    empresaId: number;
    compraId: number;
    articuloId: number;
    baseImponible: number;
    impuestoId: number;
    importeTotal: number;
}

