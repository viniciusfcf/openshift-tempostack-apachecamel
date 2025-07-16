package org.acme;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @Incoming("pagamentos")
    public void consume(String msg) {
        System.out.println("GreetingResource.consume() " + msg);
    }
}
