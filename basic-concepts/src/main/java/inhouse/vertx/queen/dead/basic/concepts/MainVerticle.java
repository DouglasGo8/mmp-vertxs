package inhouse.vertx.queen.dead.basic.concepts;

import inhouse.vertx.queen.dead.basic.concepts.service.WhiskyVerticle;
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.json.JsonObject;

import static inhouse.vertx.queen.dead.utils.VertxRunner.runVerticle;


/**
 * 
 * @author dbatista
 *
 */
public class MainVerticle {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		runVerticle(vertx -> ConfigRetriever.create(vertx, new ConfigRetrieverOptions() {
			{
				addStore(new ConfigStoreOptions() {
					{
						setType("file");
						setConfig(new JsonObject().put("path", "./src/main/conf/config.json"));
					}
				});
			}
		}).getConfig(ar -> {
			if (ar.failed()) {
				throw new IllegalArgumentException(ar.cause());
			} else {
				vertx.deployVerticle(WhiskyVerticle.class.getName(),
						new DeploymentOptions()
							.setConfig(ar.result()));
			}
		}));

	}

}
