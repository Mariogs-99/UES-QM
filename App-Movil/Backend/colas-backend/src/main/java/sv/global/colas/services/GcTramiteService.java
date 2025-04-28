package sv.global.colas.services;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import sv.global.colas.entities.GcTramite;


@ApplicationScoped
public class GcTramiteService implements PanacheRepository<GcTramite>{
    private final EntityManager entityManager;



    public GcTramiteService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<GcTramite> getAllGcTramite() {
        return GcTramite.listAll();


    }

    public GcTramite getGcTramiteById(String id) {
        return GcTramite.find("nTramiteId", id).firstResult();
    }


    
    @SuppressWarnings("unchecked")
    public List<GcTramite> getGcTramiteByS(String id){
        String query="SELECT t.* FROM gc_tramite t  \n"+
                        "INNER JOIN gc_conf_tramite c ON c.n_tramite_id = t.n_tramite_id \n"+
                        "WHERE t.b_activa = 1 \n"+
                        "AND t.b_escalamiento = 0 \n"+
                        "AND c.c_unidad_recep = :id \n";
        return entityManager.createNativeQuery(query,GcTramite.class).setParameter("id", id).getResultList();
    }
    // other methods
}