package com.github.flyaway2112.logger;

public class HeartBeatLoggerFactory {

  /**
   * Return a heartBeatLogger named corresponding to the class passed as parameter.
   *
   * @param clazz          　the returned logger will be named after clazz
   * @param intervalMillis ログを出力する間隔(ms)
   * @return heartBeatLogger
   */
  public static HeartBeatLogger getLogger(Class<?> clazz, long intervalMillis) {
    return new HeartBeatLoggerImpl(clazz, intervalMillis);
  }

}
