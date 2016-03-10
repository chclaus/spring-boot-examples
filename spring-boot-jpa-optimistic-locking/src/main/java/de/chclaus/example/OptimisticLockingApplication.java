package de.chclaus.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Christian Claus (ch.claus@me.com)
 */
@SpringBootApplication
public class OptimisticLockingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OptimisticLockingApplication.class, args);
	}
}
