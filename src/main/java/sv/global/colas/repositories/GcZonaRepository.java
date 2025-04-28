/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import sv.global.colas.entities.GcZona;
import sv.global.colas.utils.Parametros;

/**
 *
 * @author Owner
 */
public interface GcZonaRepository extends CrudRepository<GcZona, Long>{

    
    @Query("SELECT t FROM GcZona t WHERE t.ffVigencia is null ")
    public List<GcZona> getAllZonas();
    
    @Query("SELECT t FROM GcZona t WHERE t.bActiva = 1 and t.ffVigencia is null")
    public List<GcZona> getAllZonasActivas();
        
    @Query("SELECT t.sNombre FROM GcZona t where t.sNombre = ?1 and t.ffVigencia is null")
    public String getZonaByName(String sNombre);
        
    @Query("SELECT t FROM GcZona t where C_UNIDAD_RECEP = ?1 and t.ffVigencia is null order by t.sNombre ")
    public List<GcZona> getZonasByUnidad(String cunidadRecepId);
        
    @Query("SELECT t.sNombre FROM GcZona t where C_UNIDAD_RECEP = ?1 and  REPLACE(upper(t.sNombre),' ','') =REPLACE(upper(?2),' ','') and t.ffVigencia is null ")
    public String getZonasByUnidadName(String cunidadRecepId,String sNombre);
    
    /*Query modificado*/
    @Query("SELECT t FROM GcZona t where C_UNIDAD_RECEP = ?1 and  t.sNombre = ?2 and t.nZonaId != ?3 and t.ffVigencia is null")
    public GcZona getZonasByUnidadNameId(String cunidadRecepId,String sNombre, Long nZonaId);
        
    @Query(value = "SELECT max(s_nombre) FROM gc_zona where C_UNIDAD_RECEP = ?1 and ff_Vigencia is null", nativeQuery = true)
    public String getMaxZona(String unidadRecep);
        
    @Query(value = "SELECT  ZONA_SATURACION(?1 , ?2 , ?3 )", nativeQuery = true)
    public Long getSaturacionZona(String cenServ, Long zonaId,String zona);
    
    @Query(value = Parametros.SQL_ZONA_USUARIOS, nativeQuery = true)
    public List getUserPorZona(Long zonaId);
   
    /*Obtener el id a insertar*/
    @Query(value="select count(N_ZONA_ID)+1 from gc_zona",nativeQuery = true)
    public Long getTotalId();
}
