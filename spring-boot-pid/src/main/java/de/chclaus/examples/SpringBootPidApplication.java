package de.chclaus.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * An example spring-boot-application which just creates a pid file at start.
 *
 * @author Christian Claus (ch.claus@me.com)
 */
@SpringBootApplication
public class SpringBootPidApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(SpringBootPidApplication.class);
		springApplication.addListeners(new ApplicationPidFileWriter());
		springApplication.run(args);

	}
}
