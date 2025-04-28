/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.pojos.entities;

import java.math.BigInteger;
import java.util.Date;
import sv.global.colas.entities.TbUnidadRecep;

/**
 *
 * @author Owner
 */
public class CllamadoPojo {
    private static final long serialVersionUID = 1L;

    private TbUnidadRecep cUnidadRecep;
    private String unidadRecep;
    private BigInteger nNumLlamadas;
    private BigInteger nIntervaloLlamada;
    private short bActiva;
    private String sMensaje;
    private String sDescripcion;
    private Long nConfllamadoId;

    public TbUnidadRecep getcUnidadRecep() {
        return cUnidadRecep;
    }

    public void setcUnidadRecep(TbUnidadRecep cUnidadRecep) {
        this.cUnidadRecep = cUnidadRecep;
    }

    public String getUnidadRecep() {
        return unidadRecep;
    }

    public void setUnidadRecep(String unidadRecep) {
        this.unidadRecep = unidadRecep;
    }

    public BigInteger getnNumLlamadas() {
        return nNumLlamadas;
    }

    public void setnNumLlamadas(BigInteger nNumLlamadas) {
        this.nNumLlamadas = nNumLlamadas;
    }

    public BigInteger getnIntervaloLlamada() {
        return nIntervaloLlamada;
    }

    public void setnIntervaloLlamada(BigInteger nIntervaloLlamada) {
        this.nIntervaloLlamada = nIntervaloLlamada;
    }

    public short getbActiva() {
        return bActiva;
    }

    public void setbActiva(short bActiva) {
        this.bActiva = bActiva;
    }

    public String getsMensaje() {
        return sMensaje;
    }

    public void setsMensaje(String sMensaje) {
        this.sMensaje = sMensaje;
    }

    public String getsDescripcion() {
        return sDescripcion;
    }

    public void setsDescripcion(String sDescripcion) {
        this.sDescripcion = sDescripcion;
    }

    public Long getnConfllamadoId() {
        return nConfllamadoId;
    }

    public void setnConfllamadoId(Long nConfllamadoId) {
        this.nConfllamadoId = nConfllamadoId;
    }
    
}
