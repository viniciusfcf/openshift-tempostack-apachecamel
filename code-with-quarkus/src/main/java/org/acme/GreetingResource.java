package org.acme;

import org.eclipse.microprofile.reactive.messaging.Channel;

import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.MutinyEmitter;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

     @Inject
    @Channel("pagamentos")
    MutinyEmitter<String> pagamentosEmitter;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @GET
    @Path("send")
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> send(@QueryParam("msg") String msg) {
        return pagamentosEmitter.send(msg).map(x -> "ok")
                .onFailure().recoverWithItem("ko");
    }
    
}
