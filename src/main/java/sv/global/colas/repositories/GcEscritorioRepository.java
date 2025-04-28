package sv.global.colas.repositories;

import java.math.BigInteger;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import sv.global.colas.entities.GcEscritorio;
import sv.global.colas.entities.GcZona;
import sv.global.colas.entities.TbUnidadRecep;


public interface GcEscritorioRepository extends CrudRepository<GcEscritorio, Long> {
            
    @Query("SELECT e FROM GcEscritorio e where e.cIdentificador = ?1 and e.nNumEscritorio = ?2 and e.cUnidadRecep = ?3 and e.nZonaId = ?4 and e.ffVigencia is null ")
    public GcEscritorio getEscritorioByName(String cIdentificador,BigInteger nNumEscritorio, TbUnidadRecep cUnidadRecep, GcZona nZonaId);
            
    @Query("SELECT e FROM GcEscritorio e where e.cIdentificador = ?1 and e.nNumEscritorio = ?2 and e.cUnidadRecep = ?3 and e.nZonaId = ?4 and e.ffVigencia is null and  e.nEscritorioId != ?5")
    public GcEscritorio getEscritorioByNameEdit(String cIdentificador,BigInteger nNumEscritorio, TbUnidadRecep cUnidadRecep, GcZona nZonaId,Long nEscritorioId);
    
    @Query("SELECT e FROM GcEscritorio e where e.nEscritorioId not in (SELECT u.nEscritorioId FROM GcUsuario u) and c_unidad_recep= ?1 and e.ffVigencia is null")
    public List<GcEscritorio> getEscritoriosVacios(String unidadRecep);
    
    @Query("SELECT e FROM GcEscritorio e where e.nEscritorioId in (SELECT u.nEscritorioId.nEscritorioId FROM GcUsuario u where u.cUsuario in(select j.sUsuario from GcJerarquiaSeccion j)) and c_unidad_recep= ?1 and e.ffVigencia is null")
    public List<GcEscritorio> getEscritoriosJefes(String unidadRecep);
    
    @Query("SELECT e FROM GcEscritorio e where C_UNIDAD_RECEP = ?1 and e.ffVigencia is null")
    public List<GcEscritorio> getEscritoriosByUnidad(String Unidad);
    
    @Query("SELECT count(e) FROM GcEscritorio e where n_zona_id = ?1 and e.ffVigencia is null")
    public int getEscritoriosByZona(Long zona);
    
    @Query("SELECT e FROM GcEscritorio e where e.ffVigencia is null")
    public List<GcEscritorio> allEscritorios();
    
    @Query(value= " select n_escritorio_id,CONCAT(c_identificador,n_num_escritorio) escritorio \n" +
            " from gc_escritorio where c_unidad_recep=?1 \n" +
            " and n_escritorio_id not in(select n_escritorio_id from gc_usuario)\n" +
            " and b_activa=1 and ff_vigencia is null",nativeQuery=true)
    /*@Query(value= " select n_escritorio_id,c_identificador||n_num_escritorio escritorio \n" +
            " from gc_escritorio where c_unidad_recep=?1 \n" +
            " and n_escritorio_id not in(select n_escritorio_id from gc_usuario)\n" +
            " and b_activa=1 and ff_vigencia is null",nativeQuery=true)*/
    public List allEscritoriosDisponibles(String unidadRecep);
            
    @Query("SELECT e FROM GcEscritorio e where c_identificador = ?1 and n_num_escritorio = ?2 and c_unidad_recep = ?3 and n_zona_id= ?4 and e.ffVigencia is null ")
    public GcEscritorio getEscritorioByName(String cIdentificador,Long EscritorioId, String cUnidadRecep, Long nZonaId);

    /*Devolver entero que se le insertara al id*/
    @Query(value="SELECT count(N_ESCRITORIO_ID)+1 from gc_escritorio",nativeQuery =  true)
    public Long getTotalId();
}
