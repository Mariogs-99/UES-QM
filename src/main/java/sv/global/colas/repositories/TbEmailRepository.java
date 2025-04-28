/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.repositories;

import org.springframework.data.repository.CrudRepository;
import sv.global.colas.entities.TbEmail;


public interface TbEmailRepository extends CrudRepository<TbEmail, String>{
    
}
