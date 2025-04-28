package sv.global.colas.repositories;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import sv.global.colas.entities.GcTramite;
import sv.global.colas.entities.GcUsuario;
import sv.global.colas.pojos.entities.UsuarioPojo;


public class GcAdminRepositoryImpl implements GcAdminRepository {
    
    @Autowired
    private GcUsuarioRepository usuarioRepository;
    @Autowired
    private GcTramiteRepository tramiteRepository;

    @Override
    public boolean save(UsuarioPojo user) {
        
        GcUsuario usuario = new GcUsuario(user.getcUsuario());
        usuario.setNEscritorioId(user.getEscritorio());
        usuario.setGcTramiteList(user.getAddTramite());

        try {
            usuarioRepository.save(usuario);
            return true;
        } catch (Exception e) {
            System.out.println("No se ha podido guardar el usuario.");
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<GcUsuario> getAllUser() {
        
        List<GcUsuario> users = usuarioRepository.getUsers();
        for (GcUsuario u : users) {
            List<GcTramite> tramites = new ArrayList<GcTramite>();
            tramites = tramiteRepository.getTramitesByUser(u.getCUsuario());
            u.setGcTramiteList(tramites);
        }
        
        return users;
    }
    
    
    
}
