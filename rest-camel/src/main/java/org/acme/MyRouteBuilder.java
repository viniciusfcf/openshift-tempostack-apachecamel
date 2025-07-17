package org.acme;

import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        rest("/ordens")
            .post()
                .to("direct:convertBody")

            ;

        from("direct:convertBody")
            .setBody().jq("{ \"ItemName\": .ItemName, \"ItemSummary\": (.ItemName + \" - \" + (.Quantity | tostring)) }")
            .to("log:XPTO")
            .marshal().json()
            .removeHeaders("Camel*")
            .to("http://quarkus-app-my-app.apps.cluster-z5h76.z5h76.sandbox2768.opentlc.com/hello/send?httpMethod=POST&bridgeEndpoint=true")
        ;

    }
    
}
