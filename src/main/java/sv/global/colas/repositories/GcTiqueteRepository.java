package sv.global.colas.repositories;

import java.awt.print.Pageable;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import sv.global.colas.entities.GcConfTiquete;
import sv.global.colas.entities.GcTiquete;

public interface GcTiqueteRepository extends CrudRepository<GcTiquete, Long> {

    @Query("SELECT COUNT(*) FROM GcUsuario u "
            + "INNER JOIN u.gcTramiteList ut "
            + "INNER JOIN u.nEscritorioId e "
            + "INNER JOIN u.gcTramiteList tr WHERE tr.nTramiteId = ?1 and e.cUnidadRecep.cunidadRecep = ?2  ")
    public int verifyIfExistsTramiteAsignado(Long idTramite, String unidadRecepcion);

    @Query(value = "SELECT dbo.SIGUIENTE_TIQUETE(?1,?2)", nativeQuery = true)
    public String getMaxCorrelativo(Long idTramite, String unidadRecep);

    @Query("SELECT ct.nTiempoHolgura FROM GcConfTramite ct WHERE ct.nTramiteId.nTramiteId = ?1 and ct.cUnidadRecep.cunidadRecep = ?2")
    public Integer getTiempoHolgura(Long idTramite, String unidadRecepcion);

    @Query("SELECT t FROM GcTiquete t "
            + "INNER JOIN t.nTramiteId tr "
            + "INNER JOIN tr.nServiciosId s "
            + "WHERE t.nTiqueteId = (SELECT MAX(b.nTiqueteId) FROM GcTiquete b WHERE b.sCorrelativo = t.sCorrelativo) "
            + "AND dbo.TRUNC(t.fhLlegada) = dbo.TRUNC(GETDATE()) "
            + "AND t.mEstado IN(1) "
            + "AND t.cUnidadRecep.cunidadRecep IN (?1) "
            + "ORDER BY TO_NUMBER(REGEXP_REPLACE(t.sCorrelativo, '[^[:digit:]]')) DESC ")
    public List<GcTiquete> getListTiquetes(List<String> unidadRecep);

    @Query("SELECT t FROM GcTiquete t "
            + "INNER JOIN t.nTramiteId tr "
            + "INNER JOIN tr.nServiciosId s "
            + "WHERE t.mEstado = 1 AND dbo.trunc(t.fhLlegada) = dbo.trunc(GETDATE()) "
            + "AND t.cUnidadRecep.cunidadRecep IN (?1) ORDER BY TO_NUMBER(PATINDEX(t.sCorrelativo, '[^[:digit:]]')) DESC ")
    public List<GcTiquete> getListTiquetesEnEspera(List<String> unidadRecep);

    @Modifying
    @Transactional
    @Query("UPDATE GcTiquete t SET t.mEstado = 4, t.fhfProceso = GETDATE(), t.cUsuarioAtendio = ?1 WHERE t.nTiqueteId = ?2 ")
    public void changeStatusTiquete(String cUsuarioAtendio, Long tiqueteId);

    @Query("SELECT t FROM GcTiquete t INNER JOIN t.nTramiteId s INNER JOIN t.nPrioridadId p WHERE t.cUnidadRecep.cunidadRecep = ?1 AND t.mEstado = 1 AND trunc(FH_LLEGADA) = trunc(GETDATE()) AND t.nTramiteId.nTramiteId in(?2)")
    public List<GcTiquete> getNext(String cUnidadRecep, List<Long> nTramiteId);

    @Query(value = "SELECT\n"
            + "CASE \n"
            + "WHEN ctr.n_Comportamiento=1 THEN date_format(getdate(),'%d/%m/%Y %H:%i:%s')\n"
            + "WHEN ctr.n_Comportamiento=0 THEN date_format(tiq.FH_LLEGADA,'%d/%m/%Y %H:%i:%s')\n"
            + "WHEN ctr.n_Comportamiento=2 THEN date_format(tiq.FH_LLEGADA,'%d/%m/%Y %H:%i:%s')\n"
            + "END  as FECHA\n"
            + "FROM GC_TIQUETE tiq,GC_TRAMITE tr,gc_Conf_Tramite ctr\n"
            + "where tiq.N_TRAMITE_ID=tr.N_TRAMITE_ID\n"
            + "and ctr.N_TRAMITE_ID = tr.N_TRAMITE_ID\n"
            + "and tiq.N_TRAMITE_ID = ?1 AND ctr.C_UNIDAD_RECEP = ?2 AND tiq.M_ESTADO = 1 AND dbo.trunc(tiq.FH_LLEGADA) = trunc(GETDATE())  ", nativeQuery = true)
    public String getfhDateReasignacion(Long idTramite, String unidadRecep);

    @Query(value = "SELECT dbo.GENERATIQUETE(?1,?2)", nativeQuery = true)
    public String leerTiquete(String i, String u);

    @Query(value = "SELECT max(N_TIQUETE_ID) from GC_TIQUETE", nativeQuery = true)
    public String leerID();

    @Query("SELECT c FROM GcConfTiquete c "
            + " where c.bActiva=1 and c.cUnidadRecep=(select d.cUnidadRecep.cunidadRecep from GcTiquete d "
            + " where d.nTiqueteId=?1)")
    public GcConfTiquete getimg(Long i);

    @Query("SELECT t FROM GcTiquete t INNER JOIN t.nTramiteId s INNER JOIN t.nPrioridadId p WHERE t.cUnidadRecep.cunidadRecep = ?1 AND t.mEstado = 1 AND trunc(FH_LLEGADA) = trunc(GETDATE()) ORDER BY FH_LLEGADA DESC")
    public List<GcTiquete> getPruevasEnEsperaList(String cUnidadRecep);

//        @Query("SELECT SUM(((FHI_PROCESO-FH_LLEGADA)*24*60*60))/count(*) FROM GcTiquete t WHERE t.fhiProceso IS NOT NULL AND trunc(FH_LLEGADA) =trunc(getdate()) AND C_UNIDAD_RECEP in (?1) AND N_TRAMITE_ID = ?2")
    @Query(value = "SELECT SUM(((DATEDIFF(day,FHF_PROCESO,FHI_PROCESO)*24*60*60))/count(*) FROM GcTiquete t WHERE t.fhiProceso IS NOT NULL AND trunc(FH_LLEGADA) = trunc(GETDATE()) AND C_UNIDAD_RECEP in (?1) AND N_TRAMITE_ID = ?2", nativeQuery = true)
    public Double tiempoPromedioEsperaByTramite(List<String> cUnidadRecep, Long nTramiteId);

    @Query(value = "SELECT CURRENT_TIMESTAMP", nativeQuery = true)
    public Timestamp getServerDateTime();

//        @Query("SELECT SUM(((FHF_PROCESO-FHI_PROCESO)*24*60*60))/count(*) FROM GcTiquete t WHERE FHF_PROCESO IS NOT NULL AND trunc(FH_LLEGADA) =trunc(getdate()) AND C_UNIDAD_RECEP in (?1) AND N_TRAMITE_ID = ?2")
    @Query(value = "SELECT SUM(((DATEDIFF(day,FHF_PROCESO,FHI_PROCESO))*24*60*60))/count(*) FROM gc_tiquete t WHERE FHF_PROCESO IS NOT NULL AND trunc(FH_LLEGADA) = trunc(GETDATE()) AND C_UNIDAD_RECEP in (?1) AND N_TRAMITE_ID = ?2", nativeQuery = true)
    public Double tiempoPromAtencionByTramite(List<String> cUnidadRecep, Long nTramiteId);

    @Procedure(procedureName = "dbo.GC_SEND_MAIL")
    public void enviarCorreo(@Param("fromm") String fromm,
            @Param("too") String too,
            @Param("bodyy") String bodyy,
            @Param("subject") String subject,
            @Param("nombre") String nombre);

    @Query("SELECT t FROM GcTiquete t INNER JOIN t.nTramiteId s INNER JOIN t.nPrioridadId p "
            + " WHERE t.cUnidadRecep.cunidadRecep = ?1 AND t.mEstado = 2 "
            + " AND dbo.trunc(FH_LLEGADA) =dbo.trunc(GETDATE()) ORDER BY FH_LLAMADO DESC")
    public List<GcTiquete> getProximoLlamado(String cUnidadRecep);
     
    @Query(value="SELECT n_tiquete_id FROM gc_cola_llamado "
            + " WHERE c_unidad_Recep = ?1 "
            + " AND dbo.trunc(FH_LLAMADO) = dbo.trunc(GETDATE()) ORDER BY FH_LLAMADO DESC LIMIT 1" , nativeQuery = true)
    public Long getProximoTiqueteLLamar(String cUnidadRecep);
    

    @Query(value = "SELECT * FROM GC_TIQUETE t INNER JOIN GC_TRAMITE s ON t.n_tramite_id = s.n_tramite_id "
            + "INNER JOIN GC_PRIORIDAD p ON t.n_prioridad_id = p.n_prioridad_id "
            + "WHERE t.c_unidad_recep = ?1 AND t.m_estado = 2 "
            + "AND DATE(t.fh_llegada) = CURDATE() "
            + "ORDER BY t.fh_llamado DESC LIMIT 3", nativeQuery = true)
    public List<GcTiquete> getUltimosLlamados(String cUnidadRecep);

    @Query("SELECT t FROM GcTiquete t INNER JOIN t.nTramiteId s INNER JOIN t.nPrioridadId p WHERE t.cUnidadRecep.cunidadRecep = ?1 AND t.mEstado = 1 AND trunc(FH_LLEGADA) = trunc(GETDATE()) ORDER BY FH_LLEGADA DESC")
    public List<GcTiquete> getEnEsperaSeccionList(String cUnidadRecep);

    @Query(value = "call dbo.NextId(:unit_id,:operator_name);", nativeQuery = true)
    public List<Long> getNextId(@Param("unit_id") String cUnidadRecep, @Param("operator_name") String sUsuario);

    @Query(value = "SELECT * FROM gc_jerarquia_seccion js INNER JOIN gc_tiquete t on t.n_jrq_sec_id = js.n_jrq_sec_id where js.s_usuario = ?1 "
            + "AND m_estado = 1  AND dbo.TRUNC(fh_llegada)=dbo.TRUNC(getdate()) ORDER BY t.fh_llegada ASC", nativeQuery = true)
    public GcTiquete getNextEscalamiento(String cUsuario);

    @Query("SELECT COUNT(*) FROM GcConfTiquete t WHERE t.cUnidadRecep = ?1  AND t.bActiva = 1 ")
    public Integer isExistConfTiquete(String unidadRecep);
    //SE AGREGO LIMIT 1 

    @Query(value = "select ti.N_TIQUETE_ID from gc_tiquete ti where ti.S_CORRELATIVO=?1 and ti.M_ESTADO=1 and dbo.trunc(ti.FH_LLEGADA)= dbo.trunc(GETDATE())", nativeQuery = true)
    public long sTiquete(String sCorrelativo);

    /*Obtener el id a insertar*/
    @Query(value = "select max(N_TIQUETE_ID)+1 from gc_tiquete", nativeQuery = true)
    public Long getTotalId();

    @Query(value = "INSERT INTO GC_TIQUETE (C_UNIDAD_RECEP, N_TIQUETE_REA, S_CORRELATIVO, NIT, M_ESTADO, "
            + "N_JRQ_SEC_ID, FH_LLEGADA, FH_LLAMADO, FHI_PROCESO, FHF_PROCESO, C_USUARIO_ATENDIO, C_USUARIO_CREA, N_TIEMPO_HOLGURA, "
            + "N_TRAMITE_ID, N_RESERVA_CITA_ID, N_PRIORIDAD_ID) "
            + "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12, ?13, ?14, ?15, ?16)",
            nativeQuery = true)
    public GcTiquete saveTiquete(GcTiquete tiqueteData);

    @Query(value = "SELECT COUNT(*) FROM gc_tiquete WHERE c_unidad_recep = ?1 AND m_estado = '4' AND DATE(fh_llegada) = DATE(NOW())", nativeQuery = true)
    public Integer getTotalTiquetesUnidad(String cUnidadRecep);

    @Query(value = "SELECT t.S_NOMBRE AS nombre_tramite, COUNT(*) AS total_tiquetes "
            + "FROM gc_tiquete ti "
            + "JOIN gc_tramite t ON ti.N_TRAMITE_ID = t.N_TRAMITE_ID "
            + "WHERE ti.C_UNIDAD_RECEP = ?1 "
            + "AND DATE(ti.FH_LLEGADA) = CURDATE() "
            + "GROUP BY t.S_NOMBRE "
            + "ORDER BY total_tiquetes DESC "
            + "LIMIT 3", nativeQuery = true)
    public List<Object[]> getTop3TramitesWithMostTiquetes(String cUnidadRecep);

    @Query(value = "SELECT t.S_NOMBRE AS nombre_tramite "
            + "FROM gc_tiquete ti "
            + "JOIN gc_tramite t ON ti.N_TRAMITE_ID = t.N_TRAMITE_ID "
            + "WHERE ti.C_UNIDAD_RECEP = ?1 "
            + "ORDER BY ti.FH_LLEGADA DESC "
            + "LIMIT 3", nativeQuery = true)
    public List<String> getUltimos3TiquetesPorUnidad(String cUnidadRecep);

}
