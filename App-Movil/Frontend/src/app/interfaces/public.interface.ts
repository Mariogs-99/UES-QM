
export class servicios {
    BActiva: String = "";
    CUsuarioCrea: String = "";
    CUsuarioModi: String = "";
    DServicios: String = "";
    FModifica: String = "";
    NOrden: String = "";
    NServiciosId: String = "";
    SNombre: String = "";
    fiVigencia: String = "";
}

export class tramite {
    BActiva: string = "" // 1,
    BEscalamiento: string = "" // 0,
    BNitRequerido: string = "" // 1,
    CUsuarioCrea: string = "" // "ss.administrador",
    CUsuarioModi: string = "" // "ss.administrador",
    DTramite: string = "" // "Activación",
    FModifica: string = "" // "2024-07-03T22:48:41Z[UTC]",
    NIcono: string = "" // 0,
    NOrden: string = "" // 3,
    NPeso: string = "" // 1,
    NServiciosId: string = "" // "2",
    NTramiteId: string = "" // 22,
    SNombre: string = "" // "Activación",
    fiVigencia: string = "" // "2024-07-03T22:48:41Z[UTC]"
}

export class unidadreceptora {
    // bdesplegable: string = "" // true,
    // bstatus: string = "" // 1,
    // cunidad: string = "" // "80048",
    // cunidadRecep: string = "" // "80048",
    // cusuario: string = "" // "ss.administrador",
    // dunidadRecep: string = "" // "CIENCIAS ECONOMICAS",
    // mservicio: string = "" // "M",
    // mtipoUnidad: string = "" // "CENTRO DE SERVICIO"
    cunidadRecep: string = "";
    cusuario: string = "";
    dunidadRecep: string = "";
    fhingreso: string = "";
    bstatus: string = "";
    mtipoUnidad: string = "";
    bdesplegable: string = "";
    cunidad: string = "";
    cunidadDgt: string = "";
    cdepMun: string = "";
    subicGeograf: string = "";
    cunidadRecepSup: string = "";
    mservicio: string = "";
}


export class reserva {
    // N_RESERVA_CITA_ID: number = 0
    // FH_RESERVACION: string = ""
    // S_CORREO: string = ""
    // NIT: string = ""
    // N_TELEFONO: string = ""
    // S_OBSERVACIONES: string = ""
    // S_COD_VERIFICA: string = ""
    // B_ESTADO: number = 0
    // nReservaCitaId: number = 0
    // cUnidadRecep: unidadreceptora = new unidadreceptora();
    // N_TRAMITE_ID: tramite = new tramite();

    // nReservaCitaId: number = 0;
    // cUnidadRecep: unidadreceptora = new unidadreceptora()
    // fhReservacion: string = "";
    // sCorreo: string = "";
    // nit: string = "";
    // nTelefono: string = "";
    // sObservaciones: string = "";
    // sCodVerifica: string = "";
    // bEstado: number = 0;
    // nTramiteId: tramite = new tramite();

    NReservaCitaId: number = 0 // null,
    NTelefono: string = ""; // "123",
    SCorreo: string = ""; // "alcides.nolasco@gmail.com",
    SObservaciones: string = ""; // "nnone",
    bEstado: number = 0; // 1,
    cunidadRecep: number = 0; // "80048",
    fhReservacion: any; // "2024-08-16T20:00:00Z[UTC]",
    nTramiteId: number = 0; // 29,
    nit: number = 0; // "100758",
    sCodVerifica: string = ""; // ""

    // cUnidadRecep: unidadreceptora = new unidadreceptora();
    // fhReservacion: string = ""// "2022-03-10"
    // sCorreo: string = "";
    // nit: string = "";
    // nTelefono: string = "";
    // sObservaciones: string = "";
    // sCodVerifica: string = "";
    // bEstado: number = 0// 0,
    // nTramiteId: tramite = new tramite

}

export class citascl {

    NReservaCitaId: string = "";
    bEstado: string = "";
    cunidadRecep: string = "";
    fhReservacion: string = "";
    nTramiteId: string = "";
}

