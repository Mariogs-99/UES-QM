package sv.global.colas.pojos.entities;

import java.io.Serializable;
import java.util.Date;

public class PersonaPojo implements Serializable {
	
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nit;
  	 private String s1apeRasoc;
     private String s2apeAbrev;
     private String SNombres;
     private Date FNacConst;
     private Integer BDomiciliado;
     private Date FTermino;
     private Integer BInteresFiscal;
     private String MSexo;
     private Integer BImportador;
     private Double VCapitalSoc;
     private String BActivo;
     private Integer BAlerta;
     
     public PersonaPojo(){}
	
	 public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getS1apeRasoc() {
		return s1apeRasoc;
	}

	public void setS1apeRasoc(String s1apeRasoc) {
		this.s1apeRasoc = s1apeRasoc;
	}

	public String getS2apeAbrev() {
		return s2apeAbrev;
	}

	public void setS2apeAbrev(String s2apeAbrev) {
		this.s2apeAbrev = s2apeAbrev;
	}

	public String getSNombres() {
		return SNombres;
	}

	public void setSNombres(String sNombres) {
		SNombres = sNombres;
	}

	public Date getFNacConst() {
		return FNacConst;
	}

	public void setFNacConst(Date fNacConst) {
		FNacConst = fNacConst;
	}

	public Integer getBDomiciliado() {
		return BDomiciliado;
	}

	public void setBDomiciliado(Integer bDomiciliado) {
		BDomiciliado = bDomiciliado;
	}

	public Date getFTermino() {
		return FTermino;
	}

	public void setFTermino(Date fTermino) {
		FTermino = fTermino;
	}

	public Integer getBInteresFiscal() {
		return BInteresFiscal;
	}

	public void setBInteresFiscal(Integer bInteresFiscal) {
		BInteresFiscal = bInteresFiscal;
	}

	public String getMSexo() {
		return MSexo;
	}

	public void setMSexo(String mSexo) {
		MSexo = mSexo;
	}

	public Integer getBImportador() {
		return BImportador;
	}

	public void setBImportador(Integer bImportador) {
		BImportador = bImportador;
	}

	public Double getVCapitalSoc() {
		return VCapitalSoc;
	}

	public void setVCapitalSoc(Double vCapitalSoc) {
		VCapitalSoc = vCapitalSoc;
	}

	public String getBActivo() {
		return BActivo;
	}

	public void setBActivo(String bActivo) {
		BActivo = bActivo;
	}

	public Integer getBAlerta() {
		return BAlerta;
	}

	public void setBAlerta(Integer bAlerta) {
		BAlerta = bAlerta;
	}

	
}
