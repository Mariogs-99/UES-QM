package sv.global.colas;


import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import sv.global.colas.entities.GcReservaCita;
import sv.global.colas.services.GcReservaCitaService;

import java.time.LocalDateTime;
import java.util.Date;

@QuarkusTest
public class GcReservaCitaTest {
      @Inject
    GcReservaCitaService gcReservaCitaService;

    @Test
    @Transactional
    public void testInsert() {
        // Create a new GcReservaCita instance
        Date now = new Date();
        GcReservaCita reservaCita = new GcReservaCita();
        reservaCita.setCunidadRecep("80048");
        reservaCita.setFhReservacion(now);
        reservaCita.setSCorreo("test@example.com");
        reservaCita.setNit("1234567890123");
        reservaCita.setNTelefono("12345678");
        reservaCita.setSObservaciones("Test observation");
        reservaCita.setsCodVerifica("TEST123");
        reservaCita.setbEstado(6);
        reservaCita.setnTramiteId(29L);

        // Persist the entity

        gcReservaCitaService.persist(reservaCita);

        // Flush to ensure the entity is saved to the database
        

        // Assert that the entity has been assigned an ID (i.e., it was successfully persisted)
        assertNotNull(reservaCita.getNReservaCitaId());

        // Retrieve the entity from the database
        GcReservaCita retrievedReservaCita = GcReservaCita.findById(reservaCita.getNReservaCitaId());

        // Assert that the retrieved entity is not null
        assertNotNull(retrievedReservaCita);

      
    }
}