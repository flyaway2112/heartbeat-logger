package com.github.flyaway2112.logger;

/**
 * The com.github.flyaway2112.logger.HeartBeatLogger is the main user entry point of HeartBeatLogger API.
 * This outputs class/method/line number information of current executing process through slf4j API.
 *
 * <h3>Typical usage pattern:</h3>
 * <pre>
 * import com.github.flyaway2112.logger.HeartBeatLogger;
 * import com.github.flyaway2112.logger.HeartBeatLoggerFactory;
 *
 * public class LargeBatch {
 *     public void execute() {
 *         HeartBeatLogger logger = HeartBeatLoggerFactory.getLogger(this.getClass(), 100);
 *
 *         try {
 *           logger.start();
 *           // do something process
 *         } finally {
 *           logger.shutdown();
 *         }
 *     }
 * }
 * </pre>
 */
public interface HeartBeatLogger {

  /**
   * Start logging
   */
  void start();

  /**
   * Stop logging immediately
   */
  void shutdown();

}
