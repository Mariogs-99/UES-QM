package sv.global.colas.services;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import sv.global.colas.entities.GcAlumno;


@ApplicationScoped
public class GcAlumnoService implements PanacheRepository<GcAlumno>  {

    public List<GcAlumno> getAllGcAlumno() {
        return GcAlumno.listAll();


    }

    public GcAlumno getGcAlumnoById(String id) {
        return GcAlumno.find("ncarnet", id).firstResult();
    }

    // other methods
}