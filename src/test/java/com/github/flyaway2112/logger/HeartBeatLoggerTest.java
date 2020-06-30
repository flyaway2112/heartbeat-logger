package com.github.flyaway2112.logger;

import org.junit.Test;

public class HeartBeatLoggerTest {

  @Test
  public void test() throws InterruptedException {
    HeartBeatLogger logger = HeartBeatLoggerFactory.getLogger(this.getClass(), "com.github.flyaway2112", 50);
    try {
      logger.start();
      Thread.sleep(100);
      Thread.sleep(200);
      Thread.sleep(100);
    } finally {
      logger.shutdown();
    }
  }

  @Test
  public void testNullPackage() throws InterruptedException {
    HeartBeatLogger logger = HeartBeatLoggerFactory.getLogger(this.getClass(), null, 50);
    try {
      logger.start();
      Thread.sleep(100);
    } finally {
      logger.shutdown();
    }
  }

}
