/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.repositories;

import org.springframework.data.repository.Repository;
import sv.global.colas.entities.GcAlerta;

/**
 *
 * @author ever.argueta
 */
public interface GcAlertaMachRepository extends Repository<GcAlerta, Long> {
    
    public void machAlertas(String usuario, String tipo);
    
    public void readAlerta(Long id, String usuario);
    
}
