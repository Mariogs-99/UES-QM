package sv.global.colas.pojos.entities;

import java.io.Serializable;
import java.util.List;
import sv.global.colas.entities.GcTramite;


public class TramitePojo implements Serializable {
    
    private Integer nTramite;
    private String cNombre;
    private List<GcTramite> listaTramites;
    private GcTramite gcTramite;
    
    public TramitePojo() {
    }
    
    public TramitePojo(Integer nTramite, String cNombre) {
        this.nTramite = nTramite;
        this.cNombre = cNombre;
    }

    public Integer getnTramite() {
        return nTramite;
    }

    public void setnTramite(Integer nTramite) {
        this.nTramite = nTramite;
    }

    public String getcNombre() {
        return cNombre;
    }

    public void setcNombre(String cNombre) {
        this.cNombre = cNombre;
    }
    
    @Override
    public String toString() {
        return cNombre;
    }

    public List<GcTramite> getListaTramites() {
        return listaTramites;
    }

    public void setListaTramites(List<GcTramite> listaTramites) {
        this.listaTramites = listaTramites;
    }

    public GcTramite getGcTramite() {
        return gcTramite;
    }

    public void setGcTramite(GcTramite gcTramite) {
        this.gcTramite = gcTramite;
    }
    
}
