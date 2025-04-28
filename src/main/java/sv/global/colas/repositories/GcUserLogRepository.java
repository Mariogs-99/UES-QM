package sv.global.colas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import sv.global.colas.entities.GcUserLog;

public interface GcUserLogRepository extends CrudRepository<GcUserLog, Long> {
	
	@Query("SELECT t FROM GcUserLog t where t.cUsuario.cUsuario = :cUsuario AND fhfEvento IS NULL AND N_EVENTO_ID IN( '1','2','3')")
	public List<GcUserLog> enPausa(@Param("cUsuario")String cUsuario);

}
