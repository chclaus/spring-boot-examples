package de.chclaus.examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Example application with controller to demonstrate aop functionality with spring boot.
 *
 * @author Christian Claus (ch.claus@me.com)
 */
@SpringBootApplication
@RestController("/")
public class PerformanceMeasurementApplication {

	@Autowired
	private BeerService beerService;

	@RequestMapping("/drink")
	public String drink() throws InterruptedException {
		return beerService.drink();
	}

	@RequestMapping("/order")
	public String order() throws InterruptedException {
		return beerService.order();
	}

	public static void main(String[] args) {
		SpringApplication.run(PerformanceMeasurementApplication.class, args);
	}
}
