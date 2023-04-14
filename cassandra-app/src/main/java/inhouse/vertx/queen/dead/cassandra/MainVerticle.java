package inhouse.vertx.queen.dead.cassandra;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;

import static inhouse.vertx.queen.dead.utils.VertxRunner.runVerticle;


/**
 * @author dbatista
 */
public class MainVerticle extends AbstractVerticle {

  public static void main(String[] args) {

    runVerticle(runner -> {
      runner.deployVerticle(MainVerticle.class.getName(), new DeploymentOptions());
    });

  }

  @Override
  public void start() throws Exception {
    // TODO Auto-generated method stub

    /**
     * 1 - SimpleStatementVerticle
     */
    runVerticle(runner -> {
      runner.deployVerticle(SimpleStatementVerticle.class.getName(), new DeploymentOptions());
    });

    /**
     * 2 - PreparedStatementVerticle
     */
    runVerticle(runner -> {
      runner.deployVerticle(PreparedStatementVerticle.class.getName(), new DeploymentOptions());
    });

    /**
     * 3 - StreamingStatementVerticle
     */
    runVerticle(runner -> {
      runner.deployVerticle(StreamingStatementVerticle.class.getName(), new DeploymentOptions());
    });

  }

}
