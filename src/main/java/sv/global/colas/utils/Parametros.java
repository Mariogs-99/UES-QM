/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.utils;

/**
 *
 * @author Owner
 */
public class Parametros {
    public static final String SQL_TRAMITE_ESPERA="SELECT\n" +
                                            "REPLACE(CONCAT(isnull(to_char(truncn((SUM(MAXIMO)%86400)/3600),'09'),'00'),':',\n" +
                                            "isnull(to_char(truncn(((SUM(MAXIMO)%86400)%3600)/60),'09'),'00'),':',\n" +
                                            "isnull(to_char((((SUM(MAXIMO)%86400)%3600)%60),'09'),'00')),' ','') maximo_espera,\n" +
                                            "REPLACE(CONCAT(isnull(to_char(truncn((SUM(MINIMO)%86400)/3600),'09'),'00'),':',\n" +
                                            "isnull(to_char(truncn(((SUM(MINIMO)%86400)%3600)/60),'09'),'00'),':',\n" +
                                            "isnull(to_char((((SUM(MINIMO)%86400)%3600)%60),'09'),'00')),' ','') manimo_espera,\n" +
                                            "REPLACE(CONCAT(isnull(to_char(truncn((SUM(PROMEDIO)%86400)/3600),'09'),'00'),':',\n" +
                                            "isnull(to_char(truncn(((SUM(PROMEDIO)%86400)%3600)/60),'09'),'00'),':',\n" +
                                            "isnull(to_char((((SUM(PROMEDIO)%86400)%3600)%60),'09'),'00')),' ','') PROM_espera,\n" +
                                            "REPLACE(CONCAT(isnull(to_char(truncn((SUM(MODA)%86400)/3600),'09'),'00'),':',\n" +
                                            "isnull(to_char(truncn(((SUM(MODA)%86400)%3600)/60),'09'),'00'),':',\n" +
                                            "isnull(to_char((((SUM(MODA)%86400)%3600)%60),'09'),'00')),' ','') MODA_espera\n" +
                                            "FROM(SELECT MAX(DATEDIFF(d, FH_LLEGADA, isnull(FH_LLAMADO,GETDATE())))*24*60*60 MAXIMO,\n" +
                                            "MIN(DATEDIFF(d, FH_LLEGADA, isnull(FH_LLAMADO,GETDATE())))*24*60*60 MINIMO,\n" +
                                            "AVG(DATEDIFF(d, FH_LLEGADA, isnull(FH_LLAMADO,GETDATE())))*24*60*60 PROMEDIO,\n" +
                                            "0 MODA\n" +
                                            "FROM GC_TIQUETE WHERE N_TRAMITE_ID=?1\n" +
                                            "AND M_ESTADO=1 AND trunc(FH_LLEGADA)=trunc(GETDATE()) AND C_UNIDAD_RECEP=?2) SENTENCIA1";
    public static final String SQL_TRAMITE_LLAMADO="SELECT\n" +
                                            "REPLACE(CONCAT(isnull(to_char(truncn((SUM(MAXIMO)%86400)/3600),'09'),'00'),':',\n" +
                                            "isnull(to_char(truncn(((SUM(MAXIMO)%86400)%3600)/60),'09'),'00'),':',\n" +
                                            "isnull(to_char((((SUM(MAXIMO)%86400)%3600)%60),'09'),'00')),' ','') maximo_espera,\n" +
                                            "REPLACE(CONCAT(isnull(to_char(truncn((SUM(MINIMO)%86400)/3600),'09'),'00'),':',\n" +
                                            "isnull(to_char(truncn(((SUM(MINIMO)%86400)%3600)/60),'09'),'00'),':',\n" +
                                            "isnull(to_char((((SUM(MINIMO)%86400)%3600)%60),'09'),'00')),' ','') manimo_espera,\n" +
                                            "REPLACE(CONCAT(isnull(to_char(truncn((SUM(PROMEDIO)%86400)/3600),'09'),'00'),':',\n" +
                                            "isnull(to_char(truncn(((SUM(PROMEDIO)%86400)%3600)/60),'09'),'00'),':',\n" +
                                            "isnull(to_char((((SUM(PROMEDIO)%86400)%3600)%60),'09'),'00')),' ','') PROM_espera,\n" +
                                            "REPLACE(CONCAT(isnull(to_char(truncn((SUM(MODA)%86400)/3600),'09'),'00'),':',\n" +
                                            "isnull(to_char(truncn(((SUM(MODA)%86400)%3600)/60),'09'),'00'),':',\n" +
                                            "isnull(to_char((((SUM(MODA)%86400)%3600)%60),'09'),'00')),' ','') MODA_espera\n" +
                                            "FROM(SELECT MAX(DATEDIFF(d, FH_LLEGADA, isnull(FH_LLAMADO,GETDATE())))*24*60*60 MAXIMO,\n" +
                                            "MIN(DATEDIFF(d, FH_LLEGADA, isnull(FH_LLAMADO,GETDATE())))*24*60*60 MINIMO,\n" +
                                            "AVG(DATEDIFF(d, FH_LLEGADA, isnull(FH_LLAMADO,GETDATE())))*24*60*60 PROMEDIO,\n" +
                                            "0 MODA\n" +
                                            "FROM GC_TIQUETE WHERE N_TRAMITE_ID=?1\n" +
                                            "AND M_ESTADO=1 AND trunc(FH_LLEGADA)=trunc(GETDATE()) AND C_UNIDAD_RECEP=?2) SENTENCIA1";
    public static final String SQL_TRAMITE_PROCESO="SELECT\n" +
                                            "REPLACE(CONCAT(isnull(TO_CHAR(truncn((SUM(MAXIMO)%86400)/3600),'09'),'00'),':',\n" +
                                            "isnull(to_char(truncn(((SUM(MAXIMO)%86400)%3600)/60),'09'),'00'),':',\n" +
                                            "isnull(to_char((((SUM(MAXIMO)%86400)%3600)%60),'09'),'00')),' ','') maximo_espera,\n" +
                                            "REPLACE(CONCAT(isnull(to_char(truncn((SUM(MINIMO)%86400)/3600),'09'),'00'),':',\n" +
                                            "isnull(to_char(truncn(((SUM(MINIMO)%86400)%3600)/60),'09'),'00'),':',\n" +
                                            "isnull(to_char((((SUM(MINIMO)%86400)%3600)%60),'09'),'00')),' ','') manimo_espera,\n" +
                                            "REPLACE(CONCAT(isnull(to_char(truncn((SUM(PROMEDIO)%86400)/3600),'09'),'00'),':',\n" +
                                            "isnull(to_char(truncn(((SUM(PROMEDIO)%86400)%3600)/60),'09'),'00'),':',\n" +
                                            "isnull(to_char((((SUM(PROMEDIO)%86400)%3600)%60),'09'),'00')),' ','') PROM_espera,\n" +
                                            "REPLACE(CONCAT(isnull(to_char(truncn((SUM(MODA)%86400)/3600),'09'),'00'),':',\n" +
                                            "isnull(to_char(truncn(((SUM(MODA)%86400)%3600)/60),'09'),'00'),':',\n" +
                                            "isnull(to_char((((SUM(MODA)%86400)%3600)%60),'09'),'00')),' ','') MODA_espera\n" +
                                            "FROM(SELECT MAX(DATEDIFF(d, FH_LLEGADA, isnull(FH_LLAMADO,GETDATE())))*24*60*60 MAXIMO,\n" +
                                            "MIN(DATEDIFF(d, FH_LLEGADA, isnull(FH_LLAMADO,GETDATE())))*24*60*60 MINIMO,\n" +
                                            "AVG(DATEDIFF(d, FH_LLEGADA, isnull(FH_LLAMADO,GETDATE())))*24*60*60 PROMEDIO,\n" +
                                            "0 MODA\n" +
                                            "FROM GC_TIQUETE WHERE N_TRAMITE_ID=?1\n" +
                                            "AND M_ESTADO=1 AND trunc(FH_LLEGADA)=trunc(GETDATE()) AND C_UNIDAD_RECEP=?2) SENTENCIA1";
    public static final String SQL_COLAS_BY_ZONA="SELECT ax.S_NOMBRE, SUM(ax.espera) espera, SUM(ax.procesados) procesados, SUM(ax.procesando) procesando, SUM(ax.anulados) anulados,\n" +
                                            "CAST(TRAMITE_SATURACION(ax.N_TRAMITE_ID, ?1, ?2) as int) saturacion,\n" +
                                            "isnull(GROUP_CONCAT_D(DECODE(m_estado,3, tiquetes,NULL), '|'),'--') tiquete\n" +
                                            "FROM  (SELECT tra.n_tramite_id ,tra.s_nombre,\n" +
                                            "GROUP_CONCAT_D(ti.s_correlativo, '|') tiquetes,\n" +
                                            "ti.m_estado,\n" +
                                            "SUM(DECODEN(ti.m_estado,1,1,0)) espera,\n" +
                                            "SUM(DECODEN(ti.m_estado,3,1,0)) procesando,\n" +
                                            "SUM(DECODEN(ti.m_estado,4,1,0)) procesados,\n" +
                                            "SUM(DECODEN(ti.m_estado,5,1,0)) anulados\n" +
                                            "FROM gc_tiquete ti\n" +
                                            "LEFT OUTER JOIN gc_tramite tra\n" +
                                            "ON ti.n_tramite_id   =tra.n_tramite_id\n" +
                                            "WHERE m_estado      IN (1,2,3,4,5)\n" +
                                            "AND ti.n_tramite_id IN\n" +
                                            "(SELECT DISTINCT ut.n_tramite_id\n" +
                                            "FROM GC_USR_TRA ut\n" +
                                            "WHERE ut.c_usuario IN\n" +
                                            "(SELECT us.c_usuario\n" +
                                            "FROM gc_escritorio es\n" +
                                            "LEFT OUTER JOIN gc_usuario us\n" +
                                            "ON es.n_escritorio_id=us.n_escritorio_id\n" +
                                            "WHERE us.c_usuario  IS NOT NULL\n" +
                                            "AND es.n_zona_id     = ?2 ) )\n" +
                                            "AND trunc(ti.fh_llegada)=trunc(GETDATE())\n" +
                                            "AND ti.c_unidad_recep   = ?1\n" +
                                            "AND SUBSTRING(ti.s_correlativo,1,1) in(select s_nombre from gc_zona where n_zona_id = ?2)\n" +
                                            "GROUP BY tra.s_nombre,\n" +
                                            "ti.m_estado, tra.n_tramite_id) ax\n" +
                                            "GROUP BY s_nombre,n_tramite_id";
    
    public static final String SQL_TRAMITE_TOTALES="SELECT\n" +
                                            "(select count(*) from gc_tiquete where DATE_FORMAT(fhf_proceso,'yyyy-MM-dd')=DATE_FORMAT(GETDATE(),'yyyy-MM-dd') and m_estado=4 and N_tramite_id=?1  AND C_UNIDAD_RECEP=?2) PROCESADOS,\n" +
                                            "(select count(*) from gc_tiquete where DATE_FORMAT(fh_llegada,'yyyy-MM-dd')=DATE_FORMAT(GETDATE(),'yyyy-MM-dd') and m_estado=1 and N_tramite_id=?3  AND C_UNIDAD_RECEP=?4) ESPERANDO,\n" +
                                            "(SELECT COUNT(*) FROM GC_USUARIO WHERE C_USUARIO IN(SELECT C_USUARIO FROM GC_USR_TRA WHERE N_TRAMITE_ID =?5) AND N_ESCRITORIO_ID IN(SELECT N_ESCRITORIO_ID FROM GC_ESCRITORIO WHERE C_UNIDAD_RECEP=?6)) USUARIOS";
    
    public static final String SQL_USUARIOS_MONITOREO= "select ax.nombre,ax.escritorioid,ax.escritorio,ax.rol,ax.estado,ax.tramites,ax.secciones\n" +
                                            "from (SELECT us.c_usuario nombre,\n" +
                                            "es.n_escritorio_id escritorioid,\n" +
                                            "CONCAT(es.c_identificador,es.n_num_escritorio) escritorio,\n" +
                                            "'Operador' rol,\n" +
                                            "ESTADO_USUARIO(us.c_usuario) estado,\n" +
                                            "TRAMITES_USUARIO(us.c_usuario) tramites,\n" +
                                            "SECCIONES_USUARIO(us.c_usuario) secciones\n" +
                                            "FROM GC_USUARIO us\n" +
                                            "LEFT OUTER JOIN gc_escritorio es\n" +
                                            "ON us.n_escritorio_id=es.n_escritorio_id\n" +
                                            "WHERE es.n_zona_id   in (select n_zona_id from gc_zona where c_unidad_recep = ?1)) ax\n" +
                                            "where tramites is not null";
        
    public static final String SQL_ZONA_USUARIOS= "select ax.nombre, ax.escritorioid, ax.escritorio, ax.rol, ax.estado, ax.tramites, ax.secciones\n" +
                                            "from (SELECT us.c_usuario nombre,\n" +
                                            "es.n_escritorio_id escritorioid,\n" +
                                            "CONCAT(es.c_identificador,es.n_num_escritorio) escritorio,\n" +
                                            "'Operador' rol ,\n" +
                                            "ESTADO_USUARIO(us.c_usuario) estado,\n" +
                                            "TRAMITES_USUARIO(us.c_usuario) tramites,\n" +
                                            "SECCIONES_USUARIO(us.c_usuario) secciones\n" +
                                            "FROM GC_USUARIO us\n" +
                                            "LEFT OUTER JOIN gc_escritorio es\n" +
                                            "ON us.n_escritorio_id=es.n_escritorio_id\n" +
                                            "WHERE es.n_zona_id = ?1 ) ax\n" +
                                            "where tramites is not null";
    public static final String USERS_BY_CS= "select nombre, N_ZONA_ID,escritorioid,escritorio,rol, estado,tramites,secciones,\n" +
                                            "(select min(isnull(CONVERT(varchar(5),fh_llamado,108),'--'))  from gc_tiquete\n" +
                                            "where DATE_FORMAT(fh_llegada,'yyyy-MM-dd')=DATE_FORMAT(GETDATE(),'yyyy-MM-dd')\n" +
                                            "and c_usuario_atendio=nombre and fh_llamado is not null) isesion,\n" +
                                            "(select min(isnull(CONVERT(varchar(5),fh_llamado,108), '--'))  from gc_tiquete\n" +
                                            "where DATE_FORMAT(fh_llegada,'yyyy-MM-dd')=DATE_FORMAT(GETDATE(),'yyyy-MM-dd')\n" +
                                            "and c_usuario_atendio=nombre\n" +
                                            "and fh_llamado is not null) iproceso,\n" +
                                            "(select count(*) from gc_tiquete where c_usuario_atendio=nombre and DATE_FORMAT(fh_llegada,'yyyy-MM-dd')=DATE_FORMAT(GETDATE(),'yyyy-MM-dd')) PROCESADOS\n" +
                                            "from (SELECT us.c_usuario nombre,es.n_zona_id,\n" +
                                            "es.n_escritorio_id escritorioid, \n" +
                                            "CONCAT(es.c_identificador,es.n_num_escritorio) escritorio,\n" +
                                            "'Operador' rol,\n" +
                                            "ESTADO_USUARIO(us.c_usuario) estado,\n" +
                                            "TRAMITES_USUARIO(us.c_usuario) tramites,\n" +
                                            "SECCIONES_USUARIO(us.c_usuario) secciones\n" +
                                            "FROM GC_USUARIO us\n" +
                                            "LEFT OUTER JOIN gc_escritorio es\n" +
                                            "ON us.n_escritorio_id=es.n_escritorio_id\n" +
                                            "WHERE c_unidad_recep =?1) ax\n" +
                                            "where tramites is not null";
    public static final String USERS_BY_SECCION= " select n_escritorio_id,SUSTITUIR_SIMBOLOS(c_usuario) usuario,ESTADO_USUARIO(c_usuario) ESTADO \n" +
                                            "from gc_usuario where c_usuario in(select c_usuario from gc_usr_tra \n" +
                                            "where n_tramite_id in (select n_tramite_id from gc_tramite where n_servicios_id=?1))"+
                                            "AND n_escritorio_id IN(SELECT n_escritorio_id FROM GC_ESCRITORIO WHERE C_UNIDAD_RECEP=?2)";
    public static final String IDENTIFICADOR_ZONA= "SELECT z.sNombre FROM GcZona z "+
                                            "INNER JOIN z.gcEscritorioList e "+
                                            "INNER JOIN e.gcUsuarioList u "+
                                            "INNER JOIN u.gcTramiteList t "+
                                            "WHERE t.nTramiteId =?1 AND z.cUnidadRecep.cunidadRecep = ?2 ";
    public static final String SECCIONES_MONITOREO= "SELECT SECCION_MAXIMO_ESPERA(?1, ?2) MAXIMO,\n" +
"                                            SECCION_MINIMO_ESPERA(?1, ?2) MINIMO,\n" +
"                                            SECCION_PROMEDIO_ESPERA(?1, ?2) PROMEDIO,\n" +
"                                            (SELECT COUNT(*) FROM GC_TIQUETE WHERE M_ESTADO=1 AND \n" +
"                                            C_UNIDAD_RECEP=?2 AND N_TRAMITE_ID IN (SELECT N_TRAMITE_ID \n" +
"                                            FROM GC_TRAMITE WHERE N_SERVICIOS_ID=?1) AND TRUNC(FH_LLEGADA)=TRUNC(getdate())) ESPERANDO,\n" +
"                                            (SELECT COUNT(*) FROM GC_TIQUETE WHERE M_ESTADO=4 AND C_UNIDAD_RECEP=?2\n" +
"                                            AND N_TRAMITE_ID IN (SELECT N_TRAMITE_ID FROM GC_TRAMITE WHERE N_SERVICIOS_ID=?1)\n" +
"                                            AND TRUNC(FH_LLEGADA)=TRUNC(getdate())) as PROCESADOS";
    public static final String SERIE_ESPERANDO_TRAMITE="SELECT (select COUNT(*) from gc_tiquete \n" +
                                                        "where fh_llegada <= dateadd(hour,1,GETDATE())\n" +
                                                        "and (fh_llamado > dateadd(minute,45,GETDATE()) or fh_llamado is null)\n" +
                                                        "and c_unidad_recep=:unidadRecep\n" +
                                                        "and n_tramite_id=:tramiteId and m_estado=1\n" +
                                                        "and trunc(fh_llegada)=trunc(GETDATE())) HORA,\n" +
                                                        "(select COUNT(*) from gc_tiquete \n" +
                                                        "where fh_llegada <= dateadd(minute,45,getdate())\n" +
                                                        "and (fh_llamado > dateadd(hour,1,GETDATE()) or fh_llamado is null)\n" +
                                                        "and c_unidad_recep=:unidadRecep\n" +
                                                        "and n_tramite_id=:tramiteId and m_estado=1\n" +
                                                        "and trunc(fh_llegada)=trunc(GETDATE())) MIN45,\n" +
                                                        "(select COUNT(*) from gc_tiquete \n" +
                                                        "where fh_llegada <= dateadd(minute,30,GETDATE())\n" +
                                                        "and (fh_llamado > dateadd(minute,45,GETDATE()) or fh_llamado is null)\n" +
                                                        "and c_unidad_recep=:unidadRecep\n" +
                                                        "and n_tramite_id=:tramiteId and m_estado=1\n" +
                                                        "and trunc(fh_llegada)=trunc(GETDATE()) ) MIN30 ,\n" +
                                                        "(select COUNT(*) from gc_tiquete \n" +
                                                        "where fh_llegada <= dateadd(minute,15,GETDATE())\n" +
                                                        "and (fh_llamado > dateadd(minute,30,GETDATE()) or fh_llamado is null)\n" +
                                                        "and c_unidad_recep=:unidadRecep\n" +
                                                        "and n_tramite_id=:tramiteId and m_estado=1\n" +
                                                        "and trunc(fh_llegada)=trunc(GETDATE())) MIN15,\n" +
                                                        "(select COUNT(*) from gc_tiquete \n" +
                                                        "where c_unidad_recep=:unidadRecep\n" +
                                                        "and n_tramite_id=:tramiteId and m_estado=1\n" +
                                                        "and trunc(fh_llegada)=trunc(GETDATE())) ESPERANDO ";
    public static final String SECCIONES_ESPERANDO_BY_TRAMITE="    select ti.n_tramite_id, ti.s_nombre,\n" +
                                            "(select count(*) from gc_tiquete where c_unidad_recep=?1 \n" +
                                            "and n_tramite_id=ti.n_tramite_id and trunc(fh_llegada)=trunc(GETDATE()) and m_estado=1) esperando\n" +
                                            "from gc_tramite ti where n_servicios_id=?2";
}
