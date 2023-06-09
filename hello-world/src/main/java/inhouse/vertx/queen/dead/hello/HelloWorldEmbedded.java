package inhouse.vertx.queen.dead.hello;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import org.apache.log4j.Logger;
import org.jboss.resteasy.plugins.server.vertx.VertxRequestHandler;
import org.jboss.resteasy.plugins.server.vertx.VertxResteasyDeployment;

import static inhouse.vertx.queen.dead.utils.VertxRunner.runVerticle;

/**
 * @author dbatista
 */
public class HelloWorldEmbedded extends AbstractVerticle {

    /**
     * @param args
     */
    public static void main(String[] args) {

        /**
         *
         */
        runVerticle(vertx -> {
            //
            final DeploymentOptions deploymentOptions = new DeploymentOptions();
            //
            // deploymentOptions.setInstances(2);
            vertx.deployVerticle(HelloWorldEmbedded.class.getName(), deploymentOptions);
        });
    }

    /**
     *
     */
    @Override
    public void start() throws Exception {
        // TODO Auto-generated method stub
        VertxResteasyDeployment deployment = new VertxResteasyDeployment();
        //
        deployment.start();
        deployment.getRegistry().addPerInstanceResource(HelloWorldService.class);
        //

        super.vertx.createHttpServer()
                .requestHandler(new VertxRequestHandler(vertx, deployment))
                .listen(8088, ar -> {
                    Logger.getLogger(HelloWorldEmbedded.class)
                            .info("Server started on port " + ar.result().actualPort());
                });
    }

}
