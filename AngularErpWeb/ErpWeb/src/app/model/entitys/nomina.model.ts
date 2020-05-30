export interface Nomina {
    id: number;
    codigo: string;
    empresaId: number;
    empleadoId: number;
    descripcion: string;
    sueldo: number;
    extras: number;
    fechaNomina: Date;
}
