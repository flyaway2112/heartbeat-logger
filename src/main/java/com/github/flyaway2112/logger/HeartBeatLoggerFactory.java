package com.github.flyaway2112.logger;

public final class HeartBeatLoggerFactory {

  // private constructor prevents instantiation
  private HeartBeatLoggerFactory() {
    throw new AssertionError();
  }

  /**
   * Return a heartBeatLogger named corresponding to the class passed as parameter.
   *
   * @param clazz          　the returned logger will be named after clazz
   * @param targetPackage  渡されたパッケージ以下かつ最も末端に近いStackをログに出力する(nullの場合は末端のStackを出力する)
   * @param intervalMillis ログを出力する間隔(ms)
   * @return heartBeatLogger
   */
  public static HeartBeatLogger getLogger(Class<?> clazz, String targetPackage,
      long intervalMillis) {
    return new HeartBeatLoggerImpl(clazz, targetPackage, intervalMillis);
  }

}
