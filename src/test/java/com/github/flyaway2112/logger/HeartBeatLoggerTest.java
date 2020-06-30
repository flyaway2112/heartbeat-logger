package com.github.flyaway2112.logger;

import org.junit.Test;

public class HeartBeatLoggerTest {

  @Test
  public void test() throws InterruptedException {
    HeartBeatLogger logger = HeartBeatLoggerFactory.getLogger(this.getClass(), 100);
    try {
      logger.start();
      Thread.sleep(200);
      Thread.sleep(400);
      Thread.sleep(200);
    } finally {
      logger.shutdown();
    }
  }

}
