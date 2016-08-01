package de.chclaus.examples;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Interface to mark methods to be logged by the {@link LogMeasurementFilter}.
 *
 * @author Christian Claus (ch.claus@me.com)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Measure {

  /**
   * The name of the action.
   *
   * @return
   */
  String action();
}
