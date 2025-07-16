package org.acme;

import java.util.concurrent.CompletionStage;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @Inject
    Logger log; 

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @Incoming("pagamentos")
    public CompletionStage<Void> consume(Message<String> msg) {
        msg.getMetadata().forEach(log::info);
        System.out.println("GreetingResource.consume()");
        log.info(msg.getPayload());
        return msg.ack();
    }
}
