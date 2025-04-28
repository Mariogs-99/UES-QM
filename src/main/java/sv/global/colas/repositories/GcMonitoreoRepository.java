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
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import sv.global.colas.entities.GcMultimedia;
import sv.global.colas.entities.GcUsuario;
import sv.global.colas.pojos.entities.MultimediaPojo;
/**
 *
 * @author Owner
 */
public interface GcMonitoreoRepository extends CrudRepository<GcUsuario, Long>{
    
    @Query(value="select to_char(n_zona_id) from gc_zona where c_unidad_recep=?1", nativeQuery = true)
    public List<String> findZonas(String CS);
    
    @Query(value="select s_nombre from gc_zona a " +
"where n_zona_id=?1 " +
"and c_unidad_recep=?2 ", nativeQuery = true)
    /*@Query(value="select s_nombre from gc_zona a " +
"where n_zona_id=?1 " +
"and c_unidad_recep=?2 ", nativeQuery = true)*/
 public String findNzona(String zona,String CS);
    
  /*  @Query(value="select distinct to_char(n_tramite_id) from gc_usuario a " +
"inner join gc_usr_tra b on a.c_usuario=b.c_usuario " +
"inner join gc_escritorio c on a.n_escritorio_id=c.n_escritorio_id " +
"where n_zona_id=?1 " +
"and c_unidad_recep=?2 " +
"order by 1", nativeQuery = true)*/
    @Query(value="select distinct to_char(n_tramite_id) from gc_usuario a " +
"inner join gc_usr_tra b on a.c_usuario=b.c_usuario " +
"inner join gc_escritorio c on a.n_escritorio_id=c.n_escritorio_id " +
"where n_zona_id=?1 " +
"and c_unidad_recep=?2 " +
"order by 1", nativeQuery = true)
 public List<String> findtramites(String zona,String CS);
    
 @Query(value="select TRAMITE_SATURACION_ALERTA(?1,?2,?3)    ", nativeQuery = true)
 public String findalertas(String tramite,String unidad,String zona);
 
 @Query(value="select CONCAT(s_nombre,';',n_zona_id) from gc_zona where C_UNIDAD_RECEP=?1 and b_activa=1", nativeQuery = true)
 //@Query(value="select s_nombre||';'||n_zona_id from gc_zona where C_UNIDAD_RECEP=?1 and b_activa=1", nativeQuery = true)
 public List<String> zona_id(String unidad);
    
 @Query(value="select ZONA_SATURACION_ALERTA(?1,?2,?3) ", nativeQuery = true)
 public String findZonas(String unidad,String zona_id,String zona);
 
 @Query(value="select UNIDAD_SATURACION_ALERTA(?1) ", nativeQuery = true)
 public String findUnidad(String unidad);
    
}
