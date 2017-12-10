package rest;

import model.Technology;
import services.TechnologyDataService;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("technology")
public class TechnologyEndpoint {

    private static TechnologyDataService technologyDataService = TechnologyDataService.getInstance();
    private static List<Technology> technologyList;

    static {
        technologyList = technologyDataService.getTechnologyList();
    }
    @GET
    @Produces(APPLICATION_JSON)
    public Response getTechnologies() {
        return Response.status(Response.Status.OK)
                        .entity(new GenericEntity<List<Technology>>(technologyList){})
                        .build();
    }

    @GET
    @Path("/{id : \\d+}")
    @Produces(APPLICATION_JSON)
    public Response getTechnologyById(@PathParam("id") int techId){
        int offset = 1;
        Technology technology;
        try {
           technology = technologyList.get(techId - offset);
        } catch (IndexOutOfBoundsException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(technology).build();

    }


    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(TEXT_PLAIN)
    public Response createTechnology(Technology technology){
        technologyDataService.addTechnology(technology);
        return Response.status(Response.Status.CREATED)
                .entity("Creation successful")
                .build();
    }
}