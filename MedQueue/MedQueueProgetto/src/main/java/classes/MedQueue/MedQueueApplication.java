package classes.medqueue;

import config.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RestController;

/** Classe dedita all'avvio dell'applicazione. */
@SpringBootApplication(
    exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
@Import(WebSecurityConfig.class)
@ComponentScan(basePackages = "classes.controller")
@RestController
public class MedQueueApplication {

  /**
   * Avvio dell'applicazione.
   *
   * @param args Array del main
   */
  public static void main(String[] args) {
    SpringApplication.run(MedQueueApplication.class, args);
  }
}
