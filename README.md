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
    public void execute() {
        HeartBeatLogger logger = HeartBeatLoggerFactory.getLogger(this.getClass(), "com.example", 100);
        try {
          logger.start();

          // do large process
        } finally {
          logger.shutdown();
        }
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
2020-7-01 00:55:05.269 [pool-1-thread-1] DEBUG com.example.LargeBatch - Start heartbeat logger
2020-7-01 00:55:05.272 [pool-1-thread-1] INFO  com.example.LargeBatch - com.example.LargeBatch.execute(LargeBatch.java:12)
2020-7-01 00:55:05.373 [pool-1-thread-1] INFO  com.example.LargeBatch - com.example.LargeBatch.execute(LargeBatch.java:12)
2020-7-01 00:55:05.474 [pool-1-thread-1] INFO  com.example.LargeBatch - com.example.LargeBatch.execute(LargeBatch.java:12)
...
2020-7-01 00:55:15.269 [pool-1-thread-1] DEBUG com.example.LargeBatch - Shutdown heartbeat logger
```
