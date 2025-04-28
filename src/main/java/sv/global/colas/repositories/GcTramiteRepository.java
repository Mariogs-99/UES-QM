package sv.global.colas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import sv.global.colas.entities.GcTramite;
import sv.global.colas.utils.Parametros;

public interface GcTramiteRepository extends CrudRepository<GcTramite, Long> {
    
	@Query("SELECT t FROM GcTramite t INNER JOIN t.gcUsuarioList u WHERE u.cUsuario = ?1 ")
	public List<GcTramite> getTramitesByUser(String user);
	
	@Query("SELECT t FROM GcTramite t WHERE t.nServiciosId.nServiciosId = ?1 AND bEscalamiento = 1 ORDER BY nTramiteId ASC ")
	public List<GcTramite> getTramitesByServiceId(Long servicioId);
	
	@Query("SELECT t FROM GcTramite t WHERE t.nServiciosId.nServiciosId = ?1 and t.ffVigencia is null ")
	public List<GcTramite> getTramitesByService(Long servicioId);
	
	@Query("SELECT t FROM GcTramite t INNER JOIN t.nServiciosId s ORDER BY t.nServiciosId ASC ")
	public List<GcTramite> getTramitesEscalamiento();
	
	@Query("SELECT t FROM GcTramite t where t.ffVigencia is null")
	public List<GcTramite> getAllTramites();
        
//        @Query(value = Parametros.SQL_COLAS_BY_ZONA, nativeQuery = true)
//        public List getColasByZona(String unidadRecep, String zonaId);
        @Query(value = "SELECT ax.S_NOMBRE, SUM(ax.espera) espera, SUM(ax.procesados) procesados, SUM(ax.procesando) procesando, SUM(ax.anulados) anulados, \n" +
                                            "CAST(TRAMITE_SATURACION(ax.N_TRAMITE_ID, :unidadRecep, :zonaId) as int) saturacion, \n" +
                                            "isnull(GROUP_CONCAT_D(DECODE(m_estado,3, tiquetes,NULL), '|'),'--') tiquete \n" +
                                            "FROM  (SELECT tra.n_tramite_id ,tra.s_nombre, \n" +
                                            "GROUP_CONCAT_D(ti.s_correlativo, '|') tiquetes, \n" +
                                            "ti.m_estado, \n" +
                                            "SUM(DECODEN(ti.m_estado,1,1,0)) espera, \n" +
                                            "SUM(DECODEN(ti.m_estado,3,1,0)) procesando, \n" +
                                            "SUM(DECODEN(ti.m_estado,4,1,0)) procesados, \n" +
                                            "SUM(DECODEN(ti.m_estado,5,1,0)) anulados \n" +
                                            "FROM gc_tiquete ti \n" +
                                            "LEFT OUTER JOIN gc_tramite tra \n" +
                                            "ON ti.n_tramite_id   =tra.n_tramite_id \n" +
                                            "WHERE m_estado      IN (1,2,3,4,5) \n" +
                                            "AND ti.n_tramite_id IN \n" +
                                            "(SELECT DISTINCT ut.n_tramite_id \n" +
                                            "FROM GC_USR_TRA ut \n" +
                                            "WHERE ut.c_usuario IN \n" +
                                            "(SELECT us.c_usuario \n" +
                                            "FROM gc_escritorio es \n" +
                                            "LEFT OUTER JOIN gc_usuario us \n" +
                                            "ON es.n_escritorio_id=us.n_escritorio_id \n" +
                                            "WHERE us.c_usuario  IS NOT NULL \n" +
                                            "AND es.n_zona_id     = :zonaId ) ) \n" +
                                            "AND trunc(ti.fh_llegada)=trunc(GETDATE()) \n" +
                                            "AND ti.c_unidad_recep   = :unidadRecep \n" +
                                            "AND SUBSTRING(ti.s_correlativo,1,1) in(select s_nombre from gc_zona where n_zona_id = :zonaId) \n" +
                                            "GROUP BY tra.s_nombre, \n" +
                                            "ti.m_estado, tra.n_tramite_id) ax \n" +
                                            "GROUP BY s_nombre,n_tramite_id", nativeQuery = true)
        public List getColasByZona(@Param("unidadRecep")String unidadRecep, @Param("zonaId")Long zonaId);
        
        @Query(value="SELECT count(*) FROM gc_Tramite s where  REPLACE(upper(s.s_Nombre),' ','') = REPLACE(upper(?1) ,' ','') and s.n_tramite_id != ifnull(?2,0)", nativeQuery = true)
	public int getTramiteDuplicado(String tramite,Long tramiteId);
        /*Query Modificado*/
        @Query(value="select count(*) from gc_tramite tra where( (EXISTS (select * from gc_conf_tramite ct where ct.n_tramite_id=tra.n_tramite_id)) and (exists (select * from gc_usr_tra ut where ut.n_tramite_id=tra.n_tramite_id))) and tra.n_tramite_id=?1 ", nativeQuery = true)
	public int getTramiteRelacionados(Long tramiteId);
        
        @Query("SELECT t FROM GcTramite t where t.nTramiteId = ?1 ")
	public GcTramite getTramiteById(Long nTramiteId);
        
        @Query(value =  " select tra.n_tramite_id, tra.s_nombre,\n" +
                        " (select count(*) from gc_tiquete where n_tramite_id=tra.n_tramite_id \n" + 
                        " and m_estado=1 and trunc(fh_llegada)=trunc(GETDATE())  and c_unidad_recep=:unidadRecep) coun, \n" +
                        " (select count(*) from gc_tiquete where n_tramite_id=tra.n_tramite_id  \n" +
                        " and m_estado=4 and trunc(fh_llegada)=trunc(GETDATE()) and c_unidad_recep=:unidadRecep) procesados"+
                        " from gc_tramite tra where n_servicios_id=:seccionId", nativeQuery = true)
        public List getTramitesBySeccion(@Param("seccionId")Long seccionId,@Param("unidadRecep")String unidadRecep);
        
        @Query(value =  " select tra.n_tramite_id, SUSTITUIR_SIMBOLOS(tra.s_nombre),\n" +
                        " (select count(*) from gc_tiquete where n_tramite_id=tra.n_tramite_id \n" + 
                        " and m_estado=1 and trunc(fh_llegada)=trunc(getdate())  and c_unidad_recep=:unidadRecep) coun, \n" +
                        " (select count(*) from gc_tiquete where n_tramite_id=tra.n_tramite_id  \n" +
                        " and m_estado=4 and trunc(fh_llegada)=trunc(GETDATE()) and c_unidad_recep=:unidadRecep) procesados"+
                        " from gc_tramite tra where n_tramite_id=:tramiteId", nativeQuery = true)
        /*@Query(value =  " select tra.n_tramite_id, PKG_COLAS_UTILS.SUSTITUIR_SIMBOLOS(tra.s_nombre),\n" +
                        " (select count(*) from gc_tiquete where n_tramite_id=tra.n_tramite_id \n" + 
                        " and m_estado=1 and trunc(fh_llegada)=trunc(getdate())  and c_unidad_recep=:unidadRecep) coun, \n" +
                        " (select count(*) from gc_tiquete where n_tramite_id=tra.n_tramite_id  \n" +
                        " and m_estado=4 and trunc(fh_llegada)=trunc(getdate()) and c_unidad_recep=:unidadRecep) procesados"+
                        " from gc_tramite tra where n_tramite_id=:tramiteId", nativeQuery = true)*/
        public List getTramitesByEstados(@Param("tramiteId")Long seccionId,@Param("unidadRecep")String unidadRecep);
        
        @Query(value =  Parametros.SQL_TRAMITE_ESPERA, nativeQuery = true)
        public List getTramiteEspera(Long tramiteId,String unidadRecep);
        
        @Query(value =  Parametros.SQL_TRAMITE_LLAMADO, nativeQuery = true)
        public List getTramiteLlamado(Long tramiteId,String unidadRecep);
        
        @Query(value =  Parametros.SQL_TRAMITE_PROCESO, nativeQuery = true)
        public List getTramiteProceso(Long tramiteId,String unidadRecep);
        
        @Query(value =  Parametros.SQL_TRAMITE_TOTALES, nativeQuery = true)
        public List getTramiteTotales(Long tramiteId1,String unidad1,Long tramiteId2,String unidad2,Long tramiteId3,String unidad3);
        
        @Query(value=   "SELECT C_USUARIO, ESTADO_USUARIO(C_USUARIO) estado FROM GC_USUARIO \n" +
                        "WHERE C_USUARIO IN(SELECT C_USUARIO FROM GC_USR_TRA WHERE N_TRAMITE_ID =?1)  AND N_ESCRITORIO_ID IN(SELECT N_ESCRITORIO_ID FROM GC_ESCRITORIO WHERE C_UNIDAD_RECEP=?2)",nativeQuery=true)
        public List getUsuarioByTramite(Long tramiteId,String unidadRecep);
        
	@Query(value=Parametros.SERIE_ESPERANDO_TRAMITE,nativeQuery=true)
	public List getSeriesTramiteEsperando(@Param("tramiteId")Long tramiteId,@Param("unidadRecep")String unidadRecep);

	@Query("SELECT t FROM GcTramite t WHERE t.nServiciosId.nServiciosId = ?1  ORDER BY nTramiteId ASC ")
	public List<GcTramite> getTodosTramitesByServiceId(Long servicioId);
    
        @Query("SELECT ifnull(max(s.nOrden),0)+1 FROM GcTramite s where s.nServiciosId.nServiciosId = ?1")
        public Long getOrdenByTramite(Long seccion);
    
        @Query("SELECT ifnull(max(s.nOrden),0)+1 FROM GcTramite s ")
        public Long getOrdenMax();
        
        @Query(value="Select count(N_TRAMITE_ID)+1 from gc_tramite",nativeQuery = true)
        public Long getTotalId();
}
