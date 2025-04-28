/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import sv.global.colas.entities.GcPreguntas;

/**
 *
 * @author Owner
 */
public interface GcPreguntasRepository extends CrudRepository<GcPreguntas, Long> {
        
    @Query("SELECT c FROM GcPreguntas c where C_UNIDAD_RECEP = ?1 ")
    public List<GcPreguntas> preguntaYrespuestas(String cUnidadRecep);
           
    
}
