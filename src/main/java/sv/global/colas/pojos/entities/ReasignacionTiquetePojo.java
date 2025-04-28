package sv.global.colas.pojos.entities;

import java.io.Serializable;

public class ReasignacionTiquetePojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String tiqueteNo;
	private String nit;
	private String servicio;
	private String tramite;
	private String estado;
	private String fecha;
	private String hora;
	private Long idTiquete;
	private Long idTramite;
	private Long idServicio;
	private Long prioridad;
	
	
	public ReasignacionTiquetePojo(){
		
	}
	
	
	public String getTiqueteNo() {
		return tiqueteNo;
	}

	public void setTiqueteNo(String tiqueteNo) {
		this.tiqueteNo = tiqueteNo;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public String getTramite() {
		return tramite;
	}

	public void setTramite(String tramite) {
		this.tramite = tramite;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}


	public void setHora(String hora) {
		this.hora = hora;
	}
	
	public Long getIdTiquete() {
		return idTiquete;
	}

	public void setIdTiquete(Long idTiquete) {
		this.idTiquete = idTiquete;
	}

	public Long getIdTramite() {
		return idTramite;
	}

	public void setIdTramite(Long idTramite) {
		this.idTramite = idTramite;
	}

	public Long getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}


	public Long getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Long prioridad) {
		this.prioridad = prioridad;
	}

}
