package com.github.flyaway2112.logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class HeartBeatLoggerImpl implements HeartBeatLogger {

  private final Class<?> clazz;
  private final ExecutorService executor;
  private final long intervalMillis;

  HeartBeatLoggerImpl(Class<?> clazz, long intervalMillis) {
    this.clazz = clazz;
    this.executor = Executors.newCachedThreadPool();
    this.intervalMillis = intervalMillis;
  }

  @Override
  public void start() {
    executor.execute(new HeartBeatLoggerCore(clazz, Thread.currentThread(), intervalMillis));
  }

  @Override
  public void shutdown() {
    executor.shutdownNow();
  }
}
