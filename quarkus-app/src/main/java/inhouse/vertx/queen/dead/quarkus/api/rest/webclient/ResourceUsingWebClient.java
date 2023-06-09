package inhouse.vertx.queen.dead.quarkus.api.rest.webclient;



import io.vertx.axle.core.Vertx;
import io.vertx.axle.ext.web.client.WebClient;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClientOptions;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletionStage;

/**
 *
 */
@Path("/swapi")
public class ResourceUsingWebClient {

    @Inject Vertx vertx;

    /**
     *
     */
    private WebClient client;

    /**
     *
     */
    @PostConstruct
    void initialize() {
        this.client = WebClient.create(vertx,
                new WebClientOptions()
                        .setDefaultHost("swapi.co")
                        .setDefaultPort(443)
                        .setSsl(true));
    }

    /**
     *
     * @param id
     * @return
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<JsonObject> getStarWarsCharacter(@PathParam("id") int id) {
        // invoke external api
        return client.get("/api/people/" + id)
                .send()
                .thenApply(resp -> {
                    if (resp.statusCode() == 200) {
                        return resp.bodyAsJsonObject();
                    } else {
                        return new JsonObject()
                                .put("code", resp.statusCode())
                                .put("message", resp.bodyAsString());
                    }
                });
    }
}
