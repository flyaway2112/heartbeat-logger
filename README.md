# heartbeat-logger

Output class/method/line numbers information at regular intervals through slf4j API.

# Motivation

It's hard to understand the long-time process is stopping(hunging) at specific line number or not.

# Features

## Example

```java
package com.example;

import com.github.flyaway2112.logger.HeartBeatLogger;
import com.github.flyaway2112.logger.HeartBeatLoggerFactory;

public class LargeBatch {

  public void execute() throws InterruptedException {
    HeartBeatLogger logger = HeartBeatLoggerFactory.getLogger(this.getClass(), "com.example", 100);
    try {
      logger.start();

      doLargeProcess1();
      doLargeProcess2();
    } finally {
      logger.shutdown();
    }
  }


  private void doLargeProcess1() throws InterruptedException {
    Thread.sleep(200);
  }

  private void doLargeProcess2() throws InterruptedException {
    Thread.sleep(400);
  }
}
```

And set logback setting.

```
<property name="format" value="%d{yyyy-MMM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n" />

<appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
  <encoder>
    <pattern>${format}</pattern>
  </encoder>
</appender>
```

Then logger will output following log.

Outputs current class/method/line number information at regular intervals as Info level.

```
2020-7-01 00:11:23.881 [pool-1-thread-1] DEBUG com.example.LargeBatch - Start heartbeat logger
2020-7-01 00:11:23.883 [pool-1-thread-1] INFO  com.example.LargeBatch - com.example.LargeBatch.doLargeProcess1(LargeBatch.java:22)
2020-7-01 00:11:23.983 [pool-1-thread-1] INFO  com.example.LargeBatch - com.example.LargeBatch.doLargeProcess1(LargeBatch.java:22)
2020-7-01 00:11:24.084 [pool-1-thread-1] INFO  com.example.LargeBatch - com.example.LargeBatch.doLargeProcess2(LargeBatch.java:26)
2020-7-01 00:11:24.185 [pool-1-thread-1] INFO  com.example.LargeBatch - com.example.LargeBatch.doLargeProcess2(LargeBatch.java:26)
2020-7-01 00:11:24.286 [pool-1-thread-1] INFO  com.example.LargeBatch - com.example.LargeBatch.doLargeProcess2(LargeBatch.java:26)
2020-7-01 00:11:24.386 [pool-1-thread-1] INFO  com.example.LargeBatch - com.example.LargeBatch.doLargeProcess2(LargeBatch.java:26)
2020-7-01 00:11:24.480 [pool-1-thread-1] DEBUG com.example.LargeBatch - Shutdown heartbeat logger
```
