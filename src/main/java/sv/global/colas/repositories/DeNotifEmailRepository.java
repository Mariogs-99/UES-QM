/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import sv.global.colas.entities.DeNotifEmail;


public interface DeNotifEmailRepository extends CrudRepository<DeNotifEmail, Long>{
    
    @Query("Select max(o.iindice) from DeNotifEmail o ")
    public Long getLast();
}
