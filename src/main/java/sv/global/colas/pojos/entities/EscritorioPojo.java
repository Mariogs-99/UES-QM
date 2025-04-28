package sv.global.colas.pojos.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import org.springframework.util.AutoPopulatingList;
import sv.global.colas.entities.GcEscritorio;
import sv.global.colas.entities.GcZona;
import sv.global.colas.entities.TbUnidadRecep;


public class EscritorioPojo implements Serializable {
    
    private Integer cEscritorio;
    private String dEscritorio;
    
    private Long nEscritorioId;
    private String cunidadRecepId;
    private Long cZonaId;
    private TbUnidadRecep cUnidadRecep;
    private String dZona;
    private String cUsuarioCrea;
    private String cUsuarioModi;
    private Date fiVigencia;
    private BigInteger nNumEscritorio;
    private GcZona nZonaId;
    private String cIdentificador;
    private int bActiva;
    private List<TbUnidadRecep> unidadesList =  new AutoPopulatingList<TbUnidadRecep>(TbUnidadRecep.class);
    private List<GcZona> zonaList = new AutoPopulatingList<GcZona>(GcZona.class);
    private List<GcEscritorio> escritorioList=new AutoPopulatingList<GcEscritorio>(GcEscritorio.class);
    private String dunidadRecep;
    private int nNumEscritorioI;
    
    public EscritorioPojo() {
    }
    public void setEscritorio(GcEscritorio gcEscritorio) {
        this.nEscritorioId=gcEscritorio.getNEscritorioId();
        this.cUnidadRecep=gcEscritorio.getCUnidadRecep();
        this.cUsuarioModi=gcEscritorio.getCUsuarioModi();
        this.cUsuarioCrea=gcEscritorio.getCUsuarioCrea();
        this.nZonaId=gcEscritorio.getNZonaId();
        this.cIdentificador=gcEscritorio.getCIdentificador();
        this.bActiva=gcEscritorio.getBActiva();
        this.dEscritorio=gcEscritorio.getDEscritorio();
    }
    
    public EscritorioPojo(Integer cEscritorio, String dEscritorio,String cunidadRecepId,Long cZonaId,String cIdentificador,BigInteger nNumEscritorio, int bActiva) {
        this.cEscritorio = cEscritorio;
        this.dEscritorio = dEscritorio;
        this.cunidadRecepId=cunidadRecepId;
        this.cZonaId=cZonaId;
        this.cIdentificador=cIdentificador;
        this.nNumEscritorio=nNumEscritorio;
        this.bActiva=bActiva;
    }

    public Integer getcEscritorio() {
        return cEscritorio;
    }

    public void setcEscritorio(Integer cEscritorio) {
        this.cEscritorio = cEscritorio;
    }

    public String getdEscritorio() {
        return dEscritorio;
    }

    public void setdEscritorio(String dEscritorio) {
        this.dEscritorio = dEscritorio;
    }
    
    @Override
    public String toString() {
        return dEscritorio;
    }

    public Long getnEscritorioId() {
        return nEscritorioId;
    }

    public void setnEscritorioId(Long nEscritorioId) {
        this.nEscritorioId = nEscritorioId;
    }

    public TbUnidadRecep getcUnidadRecep() {
        return cUnidadRecep;
    }

    public void setcUnidadRecep(TbUnidadRecep cUnidadRecep) {
        this.cUnidadRecep = cUnidadRecep;
    }

    public String getdZona() {
        return dZona;
    }

    public void setdZona(String dZona) {
        this.dZona = dZona;
    }

    public String getcUsuarioCrea() {
        return cUsuarioCrea;
    }

    public void setcUsuarioCrea(String cUsuarioCrea) {
        this.cUsuarioCrea = cUsuarioCrea;
    }

    public String getcUsuarioModi() {
        return cUsuarioModi;
    }

    public void setcUsuarioModi(String cUsuarioModi) {
        this.cUsuarioModi = cUsuarioModi;
    }

    public Date getFiVigencia() {
        return fiVigencia;
    }

    public void setFiVigencia(Date fiVigencia) {
        this.fiVigencia = fiVigencia;
    }

    public BigInteger getnNumEscritorio() {
        return nNumEscritorio;
    }

    public void setnNumEscritorio(BigInteger nNumEscritorio) {
        this.nNumEscritorio = nNumEscritorio;
    }

    public GcZona getnZonaId() {
        return nZonaId;
    }

    public void setnZonaId(GcZona nZonaId) {
        this.nZonaId = nZonaId;
    }

    public String getcIdentificador() {
        return cIdentificador;
    }

    public void setcIdentificador(String cIdentificador) {
        this.cIdentificador = cIdentificador;
    }

    public int getbActiva() {
        return bActiva;
    }

    public void setbActiva(int bActiva) {
        this.bActiva = bActiva;
    }

    public List<TbUnidadRecep> getUnidadesList() {
        return unidadesList;
    }

    public void setUnidadesList(List<TbUnidadRecep> unidadesList) {
        this.unidadesList = unidadesList;
    }

    public List<GcZona> getZonaList() {
        return zonaList;
    }

    public void setZonaList(List<GcZona> zonaList) {
        this.zonaList = zonaList;
    }

    public List<GcEscritorio> getEscritorioList() {
        return escritorioList;
    }

    public void setEscritorioList(List<GcEscritorio> escritorioList) {
        this.escritorioList = escritorioList;
    }

    public String getCunidadRecepId() {
        return cunidadRecepId;
    }

    public void setCunidadRecepId(String cunidadRecepId) {
        this.cunidadRecepId = cunidadRecepId;
    }

    public Long getcZonaId() {
        return cZonaId;
    }

    public void setcZonaId(Long cZonaId) {
        this.cZonaId = cZonaId;
    }

    public String getDunidadRecep() {
        return dunidadRecep;
    }

    public void setDunidadRecep(String dunidadRecep) {
        this.dunidadRecep = dunidadRecep;
    }

    public int getnNumEscritorioI() {
        return nNumEscritorioI;
    }

    public void setnNumEscritorioI(int nNumEscritorioI) {
        this.nNumEscritorioI = nNumEscritorioI;
    }
    
}
