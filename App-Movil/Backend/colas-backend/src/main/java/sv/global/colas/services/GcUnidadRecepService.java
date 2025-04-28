package sv.global.colas.services;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import sv.global.colas.entities.GcUnidadRecep;

@ApplicationScoped
public class GcUnidadRecepService {

    public List<GcUnidadRecep> getAllGcUnidadRecep() {
        return GcUnidadRecep.listAll();
    }

    public GcUnidadRecep getGcUnidadRecepById(String id) {
        return GcUnidadRecep.find("cunidadRecep", id).firstResult();
    }

    // other methods
}