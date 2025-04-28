package sv.global.colas.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import sv.global.colas.utils.Parametros;

import sv.global.colas.entities.GcServicios;

public interface GcServiciosRepository extends CrudRepository<GcServicios, Long> {

    @Query("SELECT s FROM GcServicios s ORDER BY nServiciosId ASC ")
    public List<GcServicios> getAllServicios();

    @Query("SELECT s FROM GcServicios s where s.bActiva=1 ORDER BY nServiciosId ASC ")
    public List<GcServicios> getAllServiciosActivo();

    //@Query(value=Parametros.SECCIONES_MONITOREO,nativeQuery=true)
    @Query(value = "EXEC PRC_SECCION_ESPERA ?1,?2", nativeQuery = true)
    public List getMonitoreoServicio(Long servicioId, String unidadRecep);

    @Query(value = "select SECCION_SATURACION(?1, ?2)", nativeQuery = true)
    public String getSeccionSaturacion(Long servicioId, String unidadRecep);

    @Query(value = Parametros.SECCIONES_ESPERANDO_BY_TRAMITE, nativeQuery = true)
    public List getMonitoreoSecTramites(String unidadRecep, Long servicioId);

}
