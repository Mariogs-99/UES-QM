/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import sv.global.colas.entities.GcUnidadRecep;

/**
 *
 * @author Pino
 */
public interface UnidadRecepRepository extends CrudRepository<GcUnidadRecep, String>  {
    
    @Query(value="SELECT convert(max(u.C_UNIDAD_RECEP+1),char) from tb_unidad_recep u",nativeQuery = true)
    public String getMaxUnidadRecep();
    
}
