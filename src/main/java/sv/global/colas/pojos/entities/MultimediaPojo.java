/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.pojos.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import sv.global.colas.entities.GcMultimedia;



/**
 *
 * @author Owner
 */
public class MultimediaPojo implements Serializable {
    public Long nMultimediaId;
    public String sTipo;
    private Serializable xArchivo;
    public Long nDuracion;
    public String fiMultimedia;
    public String ffMultimedia;
    public String dMultimedia;
    public String cUsuarioCrea;
    public String cUsuarioModi;
    public String fiVigencia;
    public String ffVigencia;
    public String fModifica;
    public short bActiva;
    public String sRuta;
    private String sMultimedia;

    public String getsMultimedia() {
        return sMultimedia;
    }

    public void setsMultimedia(String sMultimedia) {
        this.sMultimedia = sMultimedia;
    }
    
    public List<GcMultimedia> gcMultimediaList;

    public Long getnMultimediaId() {
        return nMultimediaId;
    }

    public void setnMultimediaId(Long nMultimediaId) {
        this.nMultimediaId = nMultimediaId;
    }

    public String getsTipo() {
        return sTipo;
    }

    public void setsTipo(String sTipo) {
        this.sTipo = sTipo;
    }

    public Serializable getxArchivo() {
        return xArchivo;
    }

    public void setxArchivo(Serializable xArchivo) {
        this.xArchivo = xArchivo;
    }

    public Long getnDuracion() {
        return nDuracion;
    }

    public void setnDuracion(Long nDuracion) {
        this.nDuracion = nDuracion;
    }

    public String getFiMultimedia() {
        return fiMultimedia;
    }

    public void setFiMultimedia(String fiMultimedia) {
        this.fiMultimedia = fiMultimedia;
    }

    public String getFfMultimedia() {
        return ffMultimedia;
    }

    public void setFfMultimedia(String ffMultimedia) {
        this.ffMultimedia = ffMultimedia;
    }

    public String getdMultimedia() {
        return dMultimedia;
    }

    public void setdMultimedia(String dMultimedia) {
        this.dMultimedia = dMultimedia;
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

    public String getFiVigencia() {
        return fiVigencia;
    }

    public void setFiVigencia(String fiVigencia) {
        this.fiVigencia = fiVigencia;
    }

    public String getFfVigencia() {
        return ffVigencia;
    }

    public void setFfVigencia(String ffVigencia) {
        this.ffVigencia = ffVigencia;
    }

    public String getfModifica() {
        return fModifica;
    }

    public void setfModifica(String fModifica) {
        this.fModifica = fModifica;
    }

    public short getbActiva() {
        return bActiva;
    }

    public void setbActiva(short bActiva) {
        this.bActiva = bActiva;
    }

    public String getsRuta() {
        return sRuta;
    }

    public void setsRuta(String sRuta) {
        this.sRuta = sRuta;
    }

    public List<GcMultimedia> getGcMultimediaList() {
        return gcMultimediaList;
    }

    public void setGcMultimediaList(List<GcMultimedia> gcMultimediaList) {
        this.gcMultimediaList = gcMultimediaList;
    }
    
    
    
}
