package sv.global.colas.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import sv.global.colas.entities.GcReservaCita;


public interface GcReservaCitaRepository extends CrudRepository<GcReservaCita, Long> {

	
	@Query("SELECT rs FROM GcReservaCita rs "
			+ "WHERE TRUNC(SYSDATE) <= TRUNC(rs.fhReservacion) AND rs.cUnidadRecep.cunidadRecep IN (?1) AND rs.bEstado IN (6,7) ORDER BY TO_NUMBER(REGEXP_REPLACE(rs.sCodVerifica, '[^[:digit:]]')) DESC ")
	public List<GcReservaCita> getData(List<String> unidadRecep);
	
	
	@Modifying
	@Transactional
	@Query("UPDATE GcTiquete t SET t.mEstado = 1, t.sCorrelativo = :sCorrelativo, fhLlegada = :fhLlegada WHERE t.nReservaCitaId.nReservaCitaId = :reservaId ")
	public Integer enableTiqueteByReservacion(@Param("sCorrelativo") String sCorrelativo, @Param("reservaId") Long reservaId, @Param("fhLlegada") Date fhLlegada);
	
	
	@Modifying
	@Transactional
	@Query("UPDATE GcReservaCita r SET r.bEstado = :bEstado WHERE r.nReservaCitaId = :reservaId ")
	public Integer actualizarEstado(@Param("bEstado") Integer bEstado, @Param("reservaId") Long reservaId);
	
        
        @Query("SELECT rs FROM GcReservaCita rs "
	+ "WHERE rs.nReservaCitaId = ?1) ")
	public GcReservaCita getReservaCitaById(Long nReservaCitaID);
        
        
        @Query("SELECT count(*) FROM GcTiquete t "
	+ "WHERE t.nReservaCitaId.nReservaCitaId = ?1 ")
	public Integer verificarReserva(Long nReservaCitaID);
        
        @Procedure(procedureName = "GC_DISABLE_RESERVA_CITA")
        //@Query(value="call SIIT.PKG_COLAS_UTILS.GC_DISABLE_RESERVA_CITA(?1)",nativeQuery=true)
        public void disableReservaCitaVencidas(@Param("UNIDAD_RECEP") String unidadRecep);
	
        //TodO: se modifica esto NVL(rc.sCodVerifica, '0') YA QUE NO HAY REGISTRO
        //@Query("SELECT TO_CHAR(NVL(MAX(TO_NUMBER(REGEXP_REPLACE(NVL(rc.sCodVerifica, '0'),'[^[:digit:]]'))),0)+1) FROM GcReservaCita rc WHERE rc.cUnidadRecep.cunidadRecep =:unidadRecep")
        //@Query("SELECT TO_CHAR(NVL(MAX(TO_NUMBER(REGEXP_REPLACE(NVL(rc.sCodVerifica, '0'),'[^[:digit:]]'))),0)+1) FROM GcReservaCita rc WHERE rc.cUnidadRecep.cunidadRecep =:unidadRecep")
        //public String getCode (@Param("unidadRecep") String unidadRecep);
}
