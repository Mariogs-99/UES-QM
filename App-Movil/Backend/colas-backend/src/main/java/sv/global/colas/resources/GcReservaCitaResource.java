package sv.global.colas.resources;

import java.util.List;



import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import sv.global.colas.dtos.CitasDto;
import sv.global.colas.entities.GcReservaCita;
import sv.global.colas.entities.GcUnidadRecep;
import sv.global.colas.services.GcReservaCitaService;

@Path("/reserva-cita")
@Tag(name = "GcReservaCita", description = "Endpoints for managing GcReservaCita entities")
public class GcReservaCitaResource {

    @Inject
    GcReservaCitaService gcReservaCitaService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all GcReservaCita entities")
    @APIResponse(responseCode = "200", description = "List of GcReservaCita entities", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = GcReservaCita.class)))
    public List<GcReservaCita> getAllGcReservaCita() {
        return gcReservaCitaService.getAllGcReservaCita();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get a GcReservaCita entity by ID")
    @APIResponse(
        responseCode = "200",
        description = "The GcReservaCita entity",
        content = @Content(mediaType = MediaType.APPLICATION_JSON,
            schema = @Schema(implementation = GcReservaCita.class))
    )
    @APIResponse(
        responseCode = "404",
        description = "GcReservaCita entity not found"
    )
    public Response getGcReservaCitaById(@PathParam("id") String id) {
        GcReservaCita entity = gcReservaCitaService.getGcReservaCitaById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(entity).build();
    }


    @POST
    @Transactional
    @Operation(summary = "Insert a new GcReservaCita entity")
    @APIResponse(
        responseCode = "201",
        description = "GcReservaCita entity created",
        content = @Content(mediaType = MediaType.APPLICATION_JSON,
            schema = @Schema(implementation = GcReservaCita.class))
    )
    @APIResponse(
        responseCode = "400",
        description = "Invalid GcReservaCita entity data"
    )
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertGcReservaCita(CitasDto cita) {
        GcReservaCita gcReservaCita = new GcReservaCita();
        //gcReservaCita.setNReservaCitaId(null);
        gcReservaCita.setCunidadRecep(cita.getCunidadRecep());
        gcReservaCita.setFhReservacion(cita.getFhReservacion());
        gcReservaCita.setNTelefono(cita.getnTelefono());
        gcReservaCita.setSCorreo(cita.getsCorreo());
        gcReservaCita.setSObservaciones(cita.getsObservaciones());
        gcReservaCita.setbEstado(6);
        gcReservaCita.setnTramiteId(cita.getnTramiteId());
     
        try {
            gcReservaCita.persist();
            return Response.status(Response.Status.CREATED).entity(gcReservaCita).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

}