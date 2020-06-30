package com.github.flyaway2112.logger;

public class HeartBeatLoggerFactory {

  /**
   * Get HeartBeatLogger
   * @param clazz　the returned logger will be named after clazz
   * @param intervalMillis ログを出力する間隔(ms)
   * @return HeartBeatLogger instance
   */
  public static HeartBeatLogger getLogger(Class<?> clazz, long intervalMillis) {
    return new HeartBeatLoggerImpl(clazz, intervalMillis);
  }

}
