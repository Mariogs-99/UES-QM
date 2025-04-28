/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import sv.global.colas.entities.GcRespuestas;

/**
 *
 * @author Owner
 */
public interface GcRespuestasRepository extends CrudRepository<GcRespuestas, Long>  {
    
    @Query("SELECT c FROM GcRespuestas c where N_PREGUNTA_ID = ?1 ")
    public List<GcRespuestas> respuestasByPregutna(Long nPreguntaId);
}
