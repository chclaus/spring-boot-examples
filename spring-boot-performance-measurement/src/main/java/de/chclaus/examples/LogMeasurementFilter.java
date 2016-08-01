package de.chclaus.examples;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

/**
 * AOP Spring component which logs runtime measurements.
 *
 * @author Christian Claus (ch.claus@me.com)
 */
@Aspect
@Component
public class LogMeasurementFilter {

  /**
   * Aspect4j method which will be executed around other methods which are annotated with {@link Measure}.
   *
   * @param joinPoint the join point with the method execution.
   * @return the result of the method execution.
   * @throws Throwable
   */
  @Around("@annotation(Measure)")
  public Object logMeasurement(ProceedingJoinPoint joinPoint) throws Throwable {
    // determines the annotated action.
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();
    Measure annotation = method.getAnnotation(Measure.class);
    String measurementAction = annotation.action();

    // starts time measurement.
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
    logger.info(measurementAction + " started.");

    try {
      // execute the original method.
      return joinPoint.proceed();

    } finally {
      // stops execution measurement and log the data.
      stopWatch.stop();
      MDC.put("executionTime", stopWatch.getTotalTimeMillis());
      logger.info(measurementAction + " completed.");
      MDC.remove("executionTime");
    }
  }
}
