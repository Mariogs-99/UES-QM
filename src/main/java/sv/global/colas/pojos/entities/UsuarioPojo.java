package sv.global.colas.pojos.entities;

import java.io.Serializable;
import java.util.List;
import org.springframework.util.AutoPopulatingList;
import sv.global.colas.entities.GcEscritorio;
import sv.global.colas.entities.GcTramite;
import sv.global.colas.entities.GcUsuario;


public class UsuarioPojo implements Serializable {

    private String cUsuario;
    private String dUsuario;
    private String password;
    private GcEscritorio escritorio;
    
    private List<GcEscritorio> listadoEscritorio = 
            new AutoPopulatingList<GcEscritorio>(GcEscritorio.class);
    private List<GcTramite> listradoTramite = 
            new AutoPopulatingList<GcTramite>(GcTramite.class);
    private List<GcTramite> addTramite = 
            new AutoPopulatingList<GcTramite>(GcTramite.class);
    private List<GcUsuario> usuarioList = 
            new AutoPopulatingList<GcUsuario>(GcUsuario.class);
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getcUsuario() {
        return cUsuario;
    }

    public void setcUsuario(String cUsuario) {
        this.cUsuario = cUsuario;
    }

    public String getdUsuario() {
        return dUsuario;
    }

    public void setdUsuario(String dUsuario) {
        this.dUsuario = dUsuario;
    }

    public List<GcEscritorio> getListadoEscritorio() {
        return listadoEscritorio;
    }

    public void setListadoEscritorio(List<GcEscritorio> listadoEscritorio) {
        this.listadoEscritorio = listadoEscritorio;
    }

    public List<GcTramite> getListradoTramite() {
        return listradoTramite;
    }

    public void setListradoTramite(List<GcTramite> listradoTramite) {
        this.listradoTramite = listradoTramite;
    }

    public List<GcTramite> getAddTramite() {
        return addTramite;
    }

    public void setAddTramite(List<GcTramite> addTramite) {
        this.addTramite = addTramite;
    }
    
    @Override
    public String toString() {
        return dUsuario;
    }
    
    public GcEscritorio getEscritorio() {
        return escritorio;
    }
    
    public void setEscritorio(GcEscritorio escritorio) {
        this.escritorio = escritorio;
    }

    public List<GcUsuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<GcUsuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

}
