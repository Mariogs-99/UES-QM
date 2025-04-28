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
import sv.global.colas.entities.GcTramite;
import sv.global.colas.services.GcTramiteService;

@Path("/tramite")
@Tag(name = "GcTramite", description = "Endpoints for managing GcTramite entities")
public class GcTramiteResource {

    @Inject
    GcTramiteService gcTramiteService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all GcTramite entities")
    @APIResponse(responseCode = "200", description = "List of GcTramite entities", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = GcTramite.class)))
    public List<GcTramite> getAllGcTramite() {
        return gcTramiteService.getAllGcTramite();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get a GcTramite entity by ID")
    @APIResponse(
        responseCode = "200",
        description = "The GcTramite entity",
        content = @Content(mediaType = MediaType.APPLICATION_JSON,
            schema = @Schema(implementation = GcTramite.class))
    )
    @APIResponse(
        responseCode = "404",
        description = "GcTramite entity not found"
    )
    public Response getGcTramiteById(@PathParam("id") String id) {
        GcTramite entity = gcTramiteService.getGcTramiteById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(entity).build();
    }

    @GET
    @Path("oficina/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all GcTramite entities by oficina")
    @APIResponse(responseCode = "200", description = "List of GcTramite entities", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = GcTramite.class)))
    public List<GcTramite> getGcTramiteByCS(@PathParam("id") String id) {
        List<GcTramite> entity = gcTramiteService.getGcTramiteByS(id);
        return entity;
    }
}