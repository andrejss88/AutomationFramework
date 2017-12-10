package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("")
public class BaseEndpoint {


    @GET
    @Produces(TEXT_PLAIN)
    public String getApiInfo() {
        return "Welcome! The only endpoint of this service is the current URL + /technology";
    }
}