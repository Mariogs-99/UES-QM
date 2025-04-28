package sv.global.colas.pojos.entities;

import java.io.Serializable;

public class OpcionesPojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long tramite;
	private Long prioridad;
	private Integer holgura;
	private String nit;
	private String opt;
	private Long idTiquete;
	
	
	public OpcionesPojo(){}
	
	
	public Long getTramite() {
		return tramite;
	}


	public void setTramite(Long tramite) {
		this.tramite = tramite;
	}


	public Long getPrioridad() {
		return prioridad;
	}


	public void setPrioridad(Long prioridad) {
		this.prioridad = prioridad;
	}


	public Integer getHolgura() {
		return holgura;
	}


	public void setHolgura(Integer holgura) {
		this.holgura = holgura;
	}


	public String getNit() {
		return nit;
	}


	public void setNit(String nit) {
		this.nit = nit;
	}


	public String getOpt() {
		return opt;
	}


	public void setOpt(String opt) {
		this.opt = opt;
	}


	public Long getIdTiquete() {
		return idTiquete;
	}


	public void setIdTiquete(Long idTiquete) {
		this.idTiquete = idTiquete;
	}

    @Override
    public String toString() {
        return "OpcionesPojo{" + "tramite=" + tramite + ", prioridad=" + prioridad + ", holgura=" + holgura + ", nit=" + nit + ", opt=" + opt + ", idTiquete=" + idTiquete + '}';
    }
	
	
}
