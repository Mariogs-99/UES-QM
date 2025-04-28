/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.repositories;

import java.math.BigInteger;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import sv.global.colas.entities.GcServicios;

/**
 *
 * @author Owner
 */
public interface GcServicioRepository extends CrudRepository<GcServicios, Long>{
    
    @Query(value = "SELECT max(s.n_orden) + 1 FROM gc_servicios s WHERE isnull(s.ff_vigencia)", nativeQuery = true)
    public BigInteger getServicioMaxOrden();
      
    @Query(value = "SELECT * FROM gc_servicios s WHERE isnull(s.ff_vigencia)", nativeQuery = true)
    public List<GcServicios> getAllSecciones();
    
    @Query(value = "SELECT IFNULL(max(s.n_orden), 0) + 1 FROM gc_servicios s WHERE isnull(s.ff_vigencia)", nativeQuery = true)
    public Long getOrdenBySecciones();
    
    @Query(value="SELECT count(*) FROM gc_servicios s where EXISTS (select * from gc_tramite tra where tra.n_servicios_id= s.n_servicios_id and ff_vigencia is null) and s.n_servicios_id=?1 and ff_Vigencia is null ", nativeQuery = true)
    public Integer getServicioRelacionado(Long servisioId);
    
    @Query(value = "SELECT COUNT(*) FROM gc_servicios s WHERE REPLACE(UPPER(s.s_nombre), ' ', '') = REPLACE(UPPER(:servicio), ' ', '') AND s.n_servicios_id != IFNULL(:servicioId, 0) AND s.ff_vigencia IS NULL", nativeQuery = true)
    public Integer getServicioDuplicado(@Param("servicio") String servicio,@Param("servicioId") Long servicioId);
   
    @Query(value="Select count(N_SERVICIOS_ID)+1 from gc_servicios",nativeQuery = true)
    public Long getTotalId();
    
    }
