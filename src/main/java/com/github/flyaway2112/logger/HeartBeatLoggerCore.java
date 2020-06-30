package com.github.flyaway2112.logger;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class HeartBeatLoggerCore implements Runnable {

  private final Logger logger;
  private final Thread thread;
  private final long intervalMillis;

  /**
   * @param clazz          the returned logger will be named after clazz
   * @param thread         対象のスレッド
   * @param intervalMillis ログを出力する間隔(ms)
   */
  HeartBeatLoggerCore(Class<?> clazz, Thread thread, long intervalMillis) {
    this.logger = LoggerFactory.getLogger(clazz);
    this.thread = thread;
    this.intervalMillis = intervalMillis;
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
      // このサンプルでは、Thread.sleepが取得された場合はスキップしている
      // 出力する行は指定のパッケージ以下に制限するとよいかもしれない
      if (Objects.equals(element.getClassName(), Thread.class.getCanonicalName())) {
        continue;
      }

      return element.toString();
    }
    return "";
  }
}
