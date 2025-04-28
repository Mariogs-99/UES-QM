package sv.global.colas.repositories;

import java.util.List;
import org.springframework.data.repository.Repository;
import sv.global.colas.entities.GcUsuario;
import sv.global.colas.pojos.entities.UsuarioPojo;


public interface GcAdminRepository extends Repository<GcUsuario, String> {
    
    public boolean save(UsuarioPojo user);
    
    public List<GcUsuario> getAllUser();
    
}
