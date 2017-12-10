package rest;

import model.Technology;
import services.TechnologyDataService;
import services.impl.ListTechnologyDataService;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("technology")
public class TechnologyEndpoint {

    private static TechnologyDataService technologyDataService = new ListTechnologyDataService();
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
    @Path("/{name : \\w+}")
    @Produces(APPLICATION_JSON)
    public Response getTechnologyByName(@PathParam("name") String name){
        Technology technology = technologyDataService.getTechnology(name);

        if(technology == null){
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

    @DELETE
    @Path("/{name : \\w+}")
    public Response deleteTechnologyById(@PathParam("name") String name){
        return technologyDataService.deleteTechnology(name);
    }
}