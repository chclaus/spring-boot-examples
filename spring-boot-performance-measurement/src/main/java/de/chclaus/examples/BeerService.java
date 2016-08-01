package de.chclaus.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author Christian Claus (ch.claus@me.com)
 */
@Service
public class BeerService {

  private static final Logger LOG = LoggerFactory.getLogger(BeerService.class);

  @Measure(action = "drinking")
  public String drink() throws InterruptedException {
    String message = "skål";
    LOG.info(message);

    Thread.sleep(new Random().longs(1, 2).findFirst().getAsLong());

    return message;
  }

  @Measure(action = "ordering")
  public String order() throws InterruptedException {
    String message = "2 beer please.";
    LOG.info(message);

    Thread.sleep(new Random().longs(2, 4).findFirst().getAsLong());

    return message;
  }
}
