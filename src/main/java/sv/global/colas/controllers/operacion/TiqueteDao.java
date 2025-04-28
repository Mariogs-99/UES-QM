package sv.global.colas.controllers.operacion;

import java.io.Serializable;
import java.util.Date;

public class TiqueteDao implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private Long nTiqueteId;
    private String cUnidadRecep;
    private String nTiqueteRea;
    private String nTiqueteEsc;
    private String sCorrelativo;
    private String mEstado;
    private Date fhLlegada;
    private Date fhLlamado;
    private String fhiProceso;
    private Date fhfProceso;
    private String nTramiteId;
    private String dPrioridad;
    private Integer puntuacion;
    private Integer nTiempoHolgura;
    
    
    
    
	public String getnTiqueteEsc() {
		return nTiqueteEsc;
	}
	public void setnTiqueteEsc(String nTiqueteEsc) {
		this.nTiqueteEsc = nTiqueteEsc;
	}
	public Integer getnTiempoHolgura() {
		return nTiempoHolgura;
	}
	public void setnTiempoHolgura(Integer nTiempoHolgura) {
		this.nTiempoHolgura = nTiempoHolgura;
	}
	public Integer getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(Integer puntuacion) {
		this.puntuacion = puntuacion;
	}
	public String getdPrioridad() {
		return dPrioridad;
	}
	public void setdPrioridad(String dPrioridad) {
		this.dPrioridad = dPrioridad;
	}
	public Long getnTiqueteId() {
		return nTiqueteId;
	}
	public void setnTiqueteId(Long nTiqueteId) {
		this.nTiqueteId = nTiqueteId;
	}
	public String getcUnidadRecep() {
		return cUnidadRecep;
	}
	public void setcUnidadRecep(String cUnidadRecep) {
		this.cUnidadRecep = cUnidadRecep;
	}
	public String getnTiqueteRea() {
		return nTiqueteRea;
	}
	public void setnTiqueteRea(String integer) {
		this.nTiqueteRea = integer;
	}
	public String getsCorrelativo() {
		return sCorrelativo;
	}
	public void setsCorrelativo(String sCorrelativo) {
		this.sCorrelativo = sCorrelativo;
	}
	public String getmEstado() {
		return mEstado;
	}
	public void setmEstado(String mEstado) {
		this.mEstado = mEstado;
	}
	public Date getFhLlegada() {
		return fhLlegada;
	}
	public void setFhLlegada(Date fhLlegada) {
		this.fhLlegada = fhLlegada;
	}
	public Date getFhLlamado() {
		return fhLlamado;
	}
	public void setFhLlamado(Date fhLlamado) {
		this.fhLlamado = fhLlamado;
	}
	public String getFhiProceso() {
		return fhiProceso;
	}
	public void setFhiProceso(String fhiProceso) {
		this.fhiProceso = fhiProceso;
	}
	public Date getFhfProceso() {
		return fhfProceso;
	}
	public void setFhfProceso(Date fhfProceso) {
		this.fhfProceso = fhfProceso;
	}
	public String getnTramiteId() {
		return nTramiteId;
	}
	public void setnTramiteId(String nTramiteId) {
		this.nTramiteId = nTramiteId;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TiqueteDao [nTiqueteId=");
		builder.append(nTiqueteId);
		builder.append(", cUnidadRecep=");
		builder.append(cUnidadRecep);
		builder.append(", nTiqueteRea=");
		builder.append(nTiqueteRea);
		builder.append(", nTiqueteEsc=");
		builder.append(nTiqueteEsc);
		builder.append(", sCorrelativo=");
		builder.append(sCorrelativo);
		builder.append(", mEstado=");
		builder.append(mEstado);
		builder.append(", fhLlegada=");
		builder.append(fhLlegada);
		builder.append(", fhLlamado=");
		builder.append(fhLlamado);
		builder.append(", fhiProceso=");
		builder.append(fhiProceso);
		builder.append(", fhfProceso=");
		builder.append(fhfProceso);
		builder.append(", nTramiteId=");
		builder.append(nTramiteId);
		builder.append(", dPrioridad=");
		builder.append(dPrioridad);
		builder.append(", puntuacion=");
		builder.append(puntuacion);
		builder.append(", nTiempoHolgura=");
		builder.append(nTiempoHolgura);
		builder.append("]");
		return builder.toString();
	}
    
    

}
