package vip.bluewater.oceanix.server.components

import io.netty.handler.logging.LogLevel
import kotlinx.coroutines.*
import java.lang.IllegalStateException

class Logger {

  companion object {
    var logLevel: LogLevel = LogLevel.INFO

    private val loggerScope = CoroutineScope(Dispatchers.IO)

    private fun log(logLevel: LogLevel, msg: String, err: Throwable?) {
      val stackTrace = Thread.currentThread().stackTrace[2]
      loggerScope.launch {
//         println("$logLevel:${stackTrace.className}->${stackTrace.methodName}->Line${stackTrace.lineNumber}:  $msg")
        println("$logLevel:  $msg")
        err?.printStackTrace()
      }
    }

    fun info(msg: String, err: Throwable?) {
      if (logLevel == LogLevel.INFO) {
        log(LogLevel.INFO, msg, err)
      }
    }

    fun warn(msg: String, err: Throwable?) {
      if (logLevel == LogLevel.WARN) {
        log(LogLevel.WARN, msg, err)
      }
    }

    fun error(msg: String, err: Throwable?) {
      if (logLevel == LogLevel.ERROR) {
        log(LogLevel.ERROR, msg, err)
      }
    }
  }
}

fun initLogger() {

}


