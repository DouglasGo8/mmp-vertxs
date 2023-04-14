package inhouse.vertx.queen.dead.circuit.breaker;

import io.vertx.core.DeploymentOptions;

import static inhouse.vertx.queen.dead.utils.VertxRunner.runVerticle;

/**
 * 
 * @author dbatista
 *
 */
public class Main {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		runVerticle(vertx -> {
			
			//vertx.deployVerticle(ServerVerticle.class.getName(), new DeploymentOptions());
			
			vertx.deployVerticle(ClientVerticle.class.getName(), new DeploymentOptions());
			
		});

	}

	

}
