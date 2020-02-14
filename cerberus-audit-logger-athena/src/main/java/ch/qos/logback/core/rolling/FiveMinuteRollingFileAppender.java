/*
 * Copyright (c) 2019 Nike, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ch.qos.logback.core.rolling;

import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

/** Appender that will roll the logs every 5 minutes */
@Slf4j
public class FiveMinuteRollingFileAppender<E> extends RollingFileAppender<E> {
  private long start = System.currentTimeMillis();

  @Override
  public void rollover() {
    long currentTime = System.currentTimeMillis();
    long maxIntervalSinceLastLoggingInMillis = TimeUnit.MINUTES.toMillis(5);
    log.info("In Appender rollover");
    if ((currentTime - start) >= maxIntervalSinceLastLoggingInMillis) {
      super.rollover();
      start = System.currentTimeMillis();
      log.info("rollover happening in appender");
    }
  }
}
