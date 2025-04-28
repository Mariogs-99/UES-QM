package sv.global.colas.resources;

import java.util.List;



import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;
import sv.global.colas.entities.GcUnidadRecep;
import sv.global.colas.services.GcUnidadRecepService;

@Path("/unidad-receptora")
@Tag(name = "GcUnidadRecep", description = "Endpoints for managing GcUnidadRecep entities")
public class GcUnidadRecepResource {

    @Inject
    GcUnidadRecepService gcUnidadRecepService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all GcUnidadRecep entities")
    @APIResponse(responseCode = "200", description = "List of GcUnidadRecep entities", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = GcUnidadRecep.class)))
    public List<GcUnidadRecep> getAllGcUnidadRecep() {
        return gcUnidadRecepService.getAllGcUnidadRecep();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get a GcUnidadRecep entity by ID")
    @APIResponse(
        responseCode = "200",
        description = "The GcUnidadRecep entity",
        content = @Content(mediaType = MediaType.APPLICATION_JSON,
            schema = @Schema(implementation = GcUnidadRecep.class))
    )
    @APIResponse(
        responseCode = "404",
        description = "GcUnidadRecep entity not found"
    )
    public Response getGcUnidadRecepById(@PathParam("id") String id) {
        GcUnidadRecep entity = gcUnidadRecepService.getGcUnidadRecepById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(entity).build();
    }
}