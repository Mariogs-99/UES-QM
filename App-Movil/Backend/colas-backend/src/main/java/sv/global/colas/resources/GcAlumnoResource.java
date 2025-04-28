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
import sv.global.colas.entities.GcAlumno;
import sv.global.colas.services.GcAlumnoService;

@Path("/alumno")
@Tag(name = "GcAlumno", description = "Endpoints for managing GcAlumno entities")
public class GcAlumnoResource {

    @Inject
    GcAlumnoService gcAlumnoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all GcAlumno entities")
    @APIResponse(responseCode = "200", description = "List of GcAlumno entities", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = GcAlumno.class)))
    public List<GcAlumno> getAllGcAlumno() {
        return gcAlumnoService.getAllGcAlumno();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get a GcAlumno entity by ID")
    @APIResponse(
        responseCode = "200",
        description = "The GcAlumno entity",
        content = @Content(mediaType = MediaType.APPLICATION_JSON,
            schema = @Schema(implementation = GcAlumno.class))
    )
    @APIResponse(
        responseCode = "404",
        description = "GcAlumno entity not found"
    )
    public Response getGcAlumnoById(@PathParam("id") String id) {
        GcAlumno entity = gcAlumnoService.getGcAlumnoById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(entity).build();
    }
}