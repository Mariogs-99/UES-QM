package sv.global.colas.services;

import java.util.List;

import org.jboss.resteasy.annotations.Query;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import sv.global.colas.entities.GcServicios;


@ApplicationScoped
public class GcServiciosService implements PanacheRepository<GcServicios>{

    public List<GcServicios> getAllGcServicios() {
        return GcServicios.listAll();


    }

    public GcServicios getGcServiciosById(String id) {
        return GcServicios.find("nServiciosId", id).firstResult();
    }

    public GcServicios getGcServiciosByOficina(String id) {
        return GcServicios.find("nServiciosId", id).firstResult();
    }

   
}