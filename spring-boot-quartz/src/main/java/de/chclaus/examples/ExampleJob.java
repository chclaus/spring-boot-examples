package de.chclaus.examples;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * A simple Job Implementation which prints a log output.
 *
 * @author Christian Claus (ch.claus@me.com)
 */
public class ExampleJob extends QuartzJobBean {

  @Autowired
  private ApplicationContext applicationContext;

  private static final Logger LOG = LoggerFactory.getLogger(ExampleJob.class);

  @Override
  protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
    LOG.info("Example Job executed. url={}", context.getJobDetail().getJobDataMap().get("url"));
  }
}
