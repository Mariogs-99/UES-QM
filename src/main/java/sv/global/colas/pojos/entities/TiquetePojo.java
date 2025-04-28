/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.pojos.entities;
import java.io.Serializable;
/**
 *
 * @author agustin
 */
public class TiquetePojo implements Serializable {
    private Integer nTiquete;

    public TiquetePojo(Integer nTiquete) {
        this.nTiquete = nTiquete;
      }
    public Integer getnTiquete() {
        return nTiquete;
    }

    public void setnTiquete(Integer nTiquete) {
        this.nTiquete = nTiquete;
    }
    
}
