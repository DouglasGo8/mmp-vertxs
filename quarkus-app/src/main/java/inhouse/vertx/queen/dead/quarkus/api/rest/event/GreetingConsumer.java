package inhouse.vertx.queen.dead.quarkus.api.rest.event;


import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;


/**
 *
 */
@ApplicationScoped
public class GreetingConsumer {

    @ConsumeEvent("greeting")
    public String greeting(String name) {
        return "Hello " + name;
    }
}
