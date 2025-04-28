package sv.global.colas.pojos.entities;

import java.io.Serializable;

public class ReservaCitaPojo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoVerificacion;
	private String areaServicio;
	private String tramite;
	private String unidad;
	private String fecha;
	private String hora;
	private String nit;
	private String correo;
	private String telefono;
	private String estado;
	private Long idTiquete;
	private Long idReservaCita;
	private Long idTramite;
	
	
	public ReservaCitaPojo(){}

	
	public String getCodigoVerificacion() {
		return codigoVerificacion;
	}
	public void setCodigoVerificacion(String codigoVerificacion) {
		this.codigoVerificacion = codigoVerificacion;
	}
	public String getAreaServicio() {
		return areaServicio;
	}
	public void setAreaServicio(String areaServicio) {
		this.areaServicio = areaServicio;
	}
	public String getTramite() {
		return tramite;
	}
	public void setTramite(String tramite) {
		this.tramite = tramite;
	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
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
	public String getNit() {
		return nit;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Long getIdTiquete() {
		return idTiquete;
	}
	public void setIdTiquete(Long idTiquete) {
		this.idTiquete = idTiquete;
	}
	public Long getIdReservaCita() {
		return idReservaCita;
	}
	public void setIdReservaCita(Long idReservaCita) {
		this.idReservaCita = idReservaCita;
	}
	public Long getIdTramite() {
		return idTramite;
	}
	public void setIdTramite(Long idTramite) {
		this.idTramite = idTramite;
	}


}
