/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import sv.global.colas.entities.GcConfTramite;
import sv.global.colas.entities.GcServicios;

/**
 *
 * @author Owner
 */
public interface GcConfTramiteRepository extends CrudRepository<GcConfTramite, Long> {
    
    @Query("SELECT c FROM GcConfTramite c where C_UNIDAD_RECEP = ?1 and c.nComportamiento is not null")
    public List<GcConfTramite> listaConfiguracionesLocal(String cUnidadRecep);
    
    @Query("SELECT c FROM GcConfTramite c where c.nComportamiento is not null")
    public List<GcConfTramite> listaConfiguracionesGeneral();
    
    @Query("SELECT c FROM GcConfTramite c where c_unidad_recep = ?1 and (c.nAtencionProm > 0 or c.nTiempoEspera is not null or c.nTiempoHolgura is not null) ")
    public List<GcConfTramite> listaConfiguracionesTramiteLocal(String unidadRecep);
    
    @Query("SELECT c FROM GcConfTramite c where c.nAtencionProm > 0 or c.nTiempoEspera is not null or c.nTiempoHolgura is not null ")
    public List<GcConfTramite> listaConfiguracionesTramiteGeneral();
    
    @Query("SELECT c FROM GcConfTramite c where C_UNIDAD_RECEP = ?1 and N_TRAMITE_ID = ?2 ")
    public GcConfTramite existe(String cUnidadRecep, Long tramiteId);
    
    @Query("SELECT c FROM GcConfTramite c where C_UNIDAD_RECEP in( ?1 ) and (c.nAtencionProm > 0 or c.nTiempoEspera is not null or c.nTiempoHolgura is not null) and c.nTramiteId.bActiva = 1 and c.nTramiteId.bEscalamiento = 0 ORDER BY nTramiteId.nServiciosId.nOrden ASC ")
    public List<GcConfTramite> listaTramitesByCS(List<String> unidadRecep);
    
    @Query("SELECT c FROM GcConfTramite c where C_UNIDAD_RECEP in( ?1 ) and c.nTramiteId.nServiciosId.nServiciosId = ?2 "
    		+ " and (c.nAtencionProm > 0 or c.nTiempoEspera is not null or c.nTiempoHolgura is not null) "
    		+ " and c.nTramiteId.bActiva = 1 and c.nTramiteId.bEscalamiento = 0 ORDER BY nTramiteId.nOrden ASC ")
    public List<GcConfTramite> listaTramitesByCS2(List<String> unidadRecep, Long idServicio);
    
    @Query(value="select distinct s.* from gc_tramite t inner join gc_servicios s on s.n_servicios_id = t.n_servicios_id inner join gc_conf_tramite c on c.n_tramite_id = t.n_tramite_id where c.c_unidad_recep = ?1 and (c.n_atencion_prom > 0 or c.n_tiempo_espera is not null or c.n_tiempo_holgura is not null) and t.b_activa = 1 and t.b_escalamiento  = 0 order by s.n_orden asc ", nativeQuery = true)
    public List<GcServicios> listaServiciosByCS(List<String> unidadRecep);
    
    @Query("SELECT c FROM GcConfTramite c where C_UNIDAD_RECEP in( ?1 ) and (c.nAtencionProm > 0 or c.nTiempoEspera is not null or c.nTiempoHolgura is not null) and c.nTramiteId.bActiva = 1 and c.nTramiteId.bEscalamiento = 1 AND c.nTramiteId.nServiciosId.nServiciosId = ?2 ORDER BY nTramiteId.nServiciosId.nOrden ASC ")
    public List<GcConfTramite> listaTramitesEscByCS(List<String> unidadRecep, Long idServicio);
    
    @Query("SELECT c FROM GcConfTramite c where C_UNIDAD_RECEP in( ?1 ) and (c.nAtencionProm > 0 or c.nTiempoEspera is not null or c.nTiempoHolgura is not null) and c.nTramiteId.bActiva = 1 and c.nTramiteId.bEscalamiento = 1 ORDER BY nTramiteId.nServiciosId.nServiciosId ASC ")
    public List<GcConfTramite> listaTramitesEscalamientoByCS(List<String> unidadRecep);
    
    @Query("SELECT c FROM GcConfTramite c where C_UNIDAD_RECEP = ?1  and (c.nAtencionProm > 0 or c.nTiempoEspera is not null or c.nTiempoHolgura is not null) and c.nTramiteId.bActiva = 1 and c.nTramiteId.bEscalamiento = 0 ORDER BY nTramiteId.nServiciosId.nServiciosId ASC ")
    public List<GcConfTramite> listaTramitesByCS2(String unidadRecep);
    
    @Query("SELECT c FROM GcConfTramite c where (c.nAtencionProm > 0 or c.nTiempoEspera is not null or c.nTiempoHolgura is not null) and c.nTramiteId.bActiva = 1 and c.nTramiteId.bEscalamiento = 0 ORDER BY nTramiteId.nServiciosId.nServiciosId ASC ")
    public List<GcConfTramite> listaTramitesByCS3();
    
    @Query(value="SELECT count(N_CONF_TRA_ID)+1 From gc_conf_tramite",nativeQuery =  true)
    public Long getTotalId();
}
