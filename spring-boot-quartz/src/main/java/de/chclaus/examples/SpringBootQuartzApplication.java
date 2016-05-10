package de.chclaus.examples;

import de.chclaus.spring.boot.quartz.jobs.QuartzJob;
import de.chclaus.spring.boot.quartz.utils.TriggerUtils;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * An example application which spawns an example job.
 *
 * @author Christian Claus (ch.claus@me.com)
 */
@SpringBootApplication
public class SpringBootQuartzApplication {

  @Autowired
  private Scheduler scheduler;

  public static void main(String[] args) {
    SpringApplication.run(SpringBootQuartzApplication.class, args);
  }

  @PostConstruct
  public void init() throws SchedulerException {
    JobDetail jobDetail = QuartzJob.builder()
        .name("An example Job")
        .jobClass(ExampleJob.class)
        .putJobData("url", "http://foo.bar")
        .build();

    scheduler.scheduleJob(jobDetail, TriggerUtils.createSingleshotTrigger("exampleTrigger", 100L));
  }

}
