package com.github.flyaway2112.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class HeartBeatLoggerCore implements Runnable {

  private final Logger logger;
  private final String targetPackage;
  private final long intervalMillis;
  private final Thread thread;

  /**
   * @param clazz          the returned logger will be named after clazz
   * @param targetPackage  指定したパッケージ以下を実行中として出力する
   * @param thread         対象のスレッド
   * @param intervalMillis ログを出力する間隔(ms)
   */
  HeartBeatLoggerCore(Class<?> clazz, String targetPackage, long intervalMillis, Thread thread) {
    this.logger = LoggerFactory.getLogger(clazz);
    this.targetPackage = targetPackage;
    this.intervalMillis = intervalMillis;
    this.thread = thread;
  }

  @Override
  public void run() {
    try {
      logger.debug("Start heartbeat logger");
      while (true) {
        logger.info(getExecutingLine());
        Thread.sleep(intervalMillis);
      }
    } catch (InterruptedException e) {
      logger.debug("Shutdown heartbeat logger");
    }
  }

  /**
   * @return 実行中のクラス・メソッド・行情報
   */
  private String getExecutingLine() {
    StackTraceElement[] stack = thread.getStackTrace();
    for (StackTraceElement element : stack) {
      if (targetPackage != null && !element.getClassName().startsWith(targetPackage)) {
        continue;
      }
      return element.toString();
    }
    return "";
  }
}
