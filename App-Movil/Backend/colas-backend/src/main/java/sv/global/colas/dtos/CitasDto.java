package sv.global.colas.dtos;

import java.util.Date;


public class CitasDto {

    public CitasDto(){
    };

    public CitasDto(Long nReservaCitaId, String cunidadRecep, Date fhReservacion, String sCorreo, String nit,
            String nTelefono, String sObservaciones, String sCodVerifica, Integer bEstado, Long nTramiteId) {
        this.nReservaCitaId = nReservaCitaId;
        this.cunidadRecep = cunidadRecep;
        this.fhReservacion = fhReservacion;
        this.sCorreo = sCorreo;
        this.nit = nit;
        this.nTelefono = nTelefono;
        this.sObservaciones = sObservaciones;
        this.sCodVerifica = sCodVerifica;
        this.bEstado = bEstado;
        this.nTramiteId = nTramiteId;
    }
    private Long nReservaCitaId;
    private String cunidadRecep;
    private Date fhReservacion;
    private String sCorreo;
    private String nit;
    private String nTelefono;
    private String sObservaciones;
    private String sCodVerifica;
    private Integer bEstado;
    private Long nTramiteId;
    public Long getnReservaCitaId() {
        return nReservaCitaId;
    }
    public void setnReservaCitaId(Long nReservaCitaId) {
        this.nReservaCitaId = nReservaCitaId;
    }
    public String getCunidadRecep() {
        return cunidadRecep;
    }
    public void setCunidadRecep(String cunidadRecep) {
        this.cunidadRecep = cunidadRecep;
    }
    public Date getFhReservacion() {
        return fhReservacion;
    }
    public void setFhReservacion(Date fhReservacion) {
        this.fhReservacion = fhReservacion;
    }
    public String getsCorreo() {
        return sCorreo;
    }
    public void setsCorreo(String sCorreo) {
        this.sCorreo = sCorreo;
    }
    public String getNit() {
        return nit;
    }
    public void setNit(String nit) {
        this.nit = nit;
    }
    public String getnTelefono() {
        return nTelefono;
    }
    public void setnTelefono(String nTelefono) {
        this.nTelefono = nTelefono;
    }
    public String getsObservaciones() {
        return sObservaciones;
    }
    public void setsObservaciones(String sObservaciones) {
        this.sObservaciones = sObservaciones;
    }
    public String getsCodVerifica() {
        return sCodVerifica;
    }
    public void setsCodVerifica(String sCodVerifica) {
        this.sCodVerifica = sCodVerifica;
    }
    public Integer getbEstado() {
        return bEstado;
    }
    public void setbEstado(Integer bEstado) {
        this.bEstado = bEstado;
    }
    public Long getnTramiteId() {
        return nTramiteId;
    }
    public void setnTramiteId(Long nTramiteId) {
        this.nTramiteId = nTramiteId;
    }
    
}
