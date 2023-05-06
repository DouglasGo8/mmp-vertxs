package inhouse.vertx.queen.dead.hibernate.starter.data;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.Configuration;
import org.hibernate.reactive.provider.ReactiveServiceRegistryBuilder;
import org.hibernate.reactive.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDateTime;
import java.util.Properties;


@Slf4j
@ExtendWith(VertxExtension.class)
public class HibernateConfigurationTest {
  @Test
  void initializeHibernateWithCodeTest(Vertx vertx, VertxTestContext context) {
    // 1. Create Properties with config data
    var props = new Properties();
    props.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres");
    props.put("hibernate.connection.username", "guest");
    props.put("hibernate.connection.password", "welcome1");
    props.put("javax.persistence.schema-generation.database.action", "drop-and-create");
    props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect"); // mandatory SQL95
    props.put("hibernate.show_sql", "true");
    // 2. Create Hibernate Configuration
    var config = new Configuration();
    config.setProperties(props);
    config.addAnnotatedClass(Task.class);
    // 3. Create ServiceRegistry
    var serviceRegistry = new ReactiveServiceRegistryBuilder().applySettings(config.getProperties()).build();
    // 4. Create SessionFactory
    var session = config.buildSessionFactory(serviceRegistry).unwrap(Stage.SessionFactory.class);
    // Do something with db
    var task = new Task();
    task.setContent("Hello new Task");
    task.setCompleted(false);
    task.setUserId(1);
    task.setCreatedAt(LocalDateTime.now());
    //
    log.info("Task ID before insertion is: {} ", task.getId());
    //
    var result = session.withTransaction((s, t) -> s.persist(task)); // CompletionStage<Void>
    var future = Future.fromCompletionStage(result);
    //
    context.verify(() -> future.onFailure(context::failNow).onSuccess((r) -> {
      log.info("Task ID after insertion is: {} ", task.getId());
      context.completeNow();
    }));
    //


  }
}
