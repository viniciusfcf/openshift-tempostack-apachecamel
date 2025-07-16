package org.acme;

import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("kafka:pagamentos?brokers=my-cluster-kafka-bootstrap.my-kafka.svc.cluster.local:9092")
        .to("log:msg?showAll=true");
    }
    
}
