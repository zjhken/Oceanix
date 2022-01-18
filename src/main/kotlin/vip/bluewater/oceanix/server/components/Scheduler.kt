package vip.bluewater.oceanix.server.components

import io.netty.handler.logging.LogLevel
import io.vertx.core.Vertx
import vip.bluewater.oceanix.server.components.Logger.Companion.logLevel

fun initSchedulers(vertx: Vertx) {
  vertx.setPeriodic(5000) {
    val logLevelStr = System.getenv()["OCEANIX_LOG_LEVEL"]
    if(!logLevelStr.isNullOrBlank()) {
      logLevel = LogLevel.valueOf(logLevelStr)
    }
  }
}
