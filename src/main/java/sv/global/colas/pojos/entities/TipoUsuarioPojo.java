
package sv.global.colas.pojos.entities;

import java.io.Serializable;


public class TipoUsuarioPojo implements Serializable {
    
    private String usuario;
    private String tipo;
    
    public TipoUsuarioPojo() {}
    
    public TipoUsuarioPojo(String usuario, String tipo) {
        this.usuario = usuario;
        this.tipo = tipo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
