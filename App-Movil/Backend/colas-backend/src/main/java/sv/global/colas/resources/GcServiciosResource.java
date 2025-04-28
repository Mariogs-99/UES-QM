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
import sv.global.colas.entities.GcServicios;
import sv.global.colas.services.GcServiciosService;

@Path("/servicios")
@Tag(name = "GcServicios", description = "Endpoints for managing GcServicios entities")
public class GcServiciosResource {

    @Inject
    GcServiciosService gcServiciosService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all GcServicios entities")
    @APIResponse(responseCode = "200", description = "List of GcServicios entities", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = GcServicios.class)))
    public List<GcServicios> getAllGcServicios() {
        return gcServiciosService.getAllGcServicios();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get a GcServicios entity by ID")
    @APIResponse(
        responseCode = "200",
        description = "The GcServicios entity",
        content = @Content(mediaType = MediaType.APPLICATION_JSON,
            schema = @Schema(implementation = GcServicios.class))
    )
    @APIResponse(
        responseCode = "404",
        description = "GcServicios entity not found"
    )
    public Response getGcServiciosById(@PathParam("id") String id) {
        GcServicios entity = gcServiciosService.getGcServiciosById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(entity).build();
    }
}