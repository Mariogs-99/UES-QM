package sv.global.colas.controllers.operacion;

import java.io.Serializable;

public class Botones implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String btnLlamarSiguiente;
	private String btnTramite;
	private String btnReasignacion;
	private String btnPausa;
	private String btnEscalamiento;
	private String btnSalir;
	
	public String getBtnLlamarSiguiente() {
		return btnLlamarSiguiente;
	}
	public void setBtnLlamarSiguiente(String btnLlamarSiguiente) {
		this.btnLlamarSiguiente = btnLlamarSiguiente;
	}
	public String getBtnTramite() {
		return btnTramite;
	}
	public void setBtnTramite(String btnTramite) {
		this.btnTramite = btnTramite;
	}
	public String getBtnReasignacion() {
		return btnReasignacion;
	}
	public void setBtnReasignacion(String btnReasignacion) {
		this.btnReasignacion = btnReasignacion;
	}
	public String getBtnPausa() {
		return btnPausa;
	}
	public void setBtnPausa(String btnPausa) {
		this.btnPausa = btnPausa;
	}
	public String getBtnEscalamiento() {
		return btnEscalamiento;
	}
	public void setBtnEscalamiento(String btnEscalamiento) {
		this.btnEscalamiento = btnEscalamiento;
	}
	public String getBtnSalir() {
		return btnSalir;
	}
	public void setBtnSalir(String btnSalir) {
		this.btnSalir = btnSalir;
	}
	
	

}
