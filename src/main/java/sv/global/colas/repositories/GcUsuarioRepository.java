package sv.global.colas.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import sv.global.colas.entities.GcUsuario;
import sv.global.colas.utils.Parametros;


public interface GcUsuarioRepository extends CrudRepository<GcUsuario, String> {
    
	@Query("SELECT u FROM GcUsuario u")
	public List<GcUsuario> getUsers();
	

	@Query("SELECT count(u) FROM GcUsuario u where n_escritorio_id= ?1")
	public int getUsersEscritorio(Long escritorio);
        
        @Query(value =Parametros.USERS_BY_SECCION, nativeQuery = true)
        public List getUsuariosBySeccion(Long seccionId,String unidadRecep);
	
	@Query(value=Parametros.USERS_BY_CS,nativeQuery=true)
        public List getUsuariosbyCS(String unidadRecep);
        
        @Query(value="SELECT g.nEscritorioId.nEscritorioId FROM GcUsuario g WHERE g.cUsuario=?1")
        public Long getEscritorioPorUsuario(String usuario);
}
