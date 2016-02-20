package de.chclaus.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Example application to demonstrate user impersonation with spring-boot and spring-security.
 *
 * @author chclaus (ch.claus@me.com)
 */
@SpringBootApplication
public class SpringBootUserImpersonationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootUserImpersonationApplication.class, args);
	}
}
