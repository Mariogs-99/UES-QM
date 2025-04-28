package sv.global.colas.services;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import sv.global.colas.entities.GcAlumno;
import sv.global.colas.entities.GcReservaCita;
import sv.global.colas.entities.GcUnidadRecep;

@ApplicationScoped
public class GcReservaCitaService implements PanacheRepository<GcReservaCita> {
     private final EntityManager entityManager;

     
    public GcReservaCitaService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<GcReservaCita> getAllGcReservaCita() {
        return GcReservaCita.listAll();
    }


    public GcReservaCita getGcReservaCitaById(String id) {
        return GcReservaCita.find("nReservaCitaId", id).firstResult();
    }

    @Transactional
    public GcReservaCita insertGcReservaCita(GcReservaCita cita) {
        // Perform any necessary validation here
        if (cita.getCunidadRecep() == null||cita.getCunidadRecep()=="") {
            throw new IllegalArgumentException("Unidad receptora cannot be null or empty");
        }

        // Check if an entity with the same ID already exists
        if (GcReservaCita.findById(cita.getNReservaCitaId()) != null) {
            throw new IllegalArgumentException("An entity with the same cunidadRecep already exists");
        }

        // Set any default values or perform any necessary transformations
        entityManager.persist(cita);
        return cita;
    }


    public EntityManager getEntityManager() {
        return entityManager;
    }
}