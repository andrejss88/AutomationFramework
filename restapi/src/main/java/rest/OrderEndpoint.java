package rest;

import model.Order;
import services.OrderDataService;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("orders")
public class OrderEndpoint {

    private static OrderDataService orderDataService = OrderDataService.getInstance();
    private static List<Order> orderList;

    static {
        orderList = orderDataService.getOrderList();
    }
    @GET
    @Produces(APPLICATION_JSON)
    public Response getOrders() {
        return Response.status(Response.Status.OK)
                        .entity(new GenericEntity<List<Order>>(orderList){})
                        .build();
    }

    @GET
    @Path("/{id : \\d+}")
    @Produces(APPLICATION_JSON)
    public Response getOrderById(@PathParam("id") int orderId){
        int offset = 1;
        Order order;
        try {
           order = orderList.get(orderId - offset);
        } catch (IndexOutOfBoundsException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(order).build();

    }

    @GET
    @Path("/{orderId}/items")
    public Response getItemsOfOrder(@PathParam("orderId")String orderId){
        return Response.status(Response.Status.OK)
                .entity("returning order id: " + orderId + ". It has items A, B and C")
                .build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(TEXT_PLAIN)
    public Response createOrder(Order order){
        orderDataService.addOrder(order);
        return Response.status(Response.Status.CREATED)
                .entity("Creation successful")
                .build();
    }
}