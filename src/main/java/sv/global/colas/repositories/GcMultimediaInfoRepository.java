/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.repositories;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Blob;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;
import sv.global.colas.entities.GcMultimedia;
import sv.global.colas.entities.GcUsuario;
import sv.global.colas.pojos.entities.MultimediaPojo;
/**
 *
 * @author Owner
 */
public interface GcMultimediaInfoRepository {
    
    public String getArchivo(String id,EntityManager em);
   
}
