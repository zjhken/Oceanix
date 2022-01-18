package vip.bluewater.oceanix.server

import io.vertx.core.Vertx
import io.vertx.core.http.HttpServer
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.await
import vip.bluewater.oceanix.server.components.*
import vip.bluewater.oceanix.server.config.appConfig
import vip.bluewater.oceanix.server.config.initConfig

class MainVerticle : CoroutineVerticle() {

  override suspend fun start() {

    initLogger()
    initSshClient()
    initDatabaseConnection()
    initConfig()
    val router = initRouter(vertx)
    initSchedulers(vertx)

    Runtime.getRuntime().addShutdownHook(Thread {
      Logger.info("Closing SSH client.", null)
      sshClient?.close()
    })

    vertx
      .createHttpServer()
      .requestHandler(router)
      .listen(appConfig.port)
      .onSuccess {
        Logger.info("HTTP server started on port ${appConfig.port}", null)
      }
      .onFailure {
        Logger.error("Failed to start service", it)
      }
      .await()
  }
}

suspend fun main() {
  Vertx.vertx().deployVerticle(MainVerticle())
    .onFailure {
      throw it
    }
    .onSuccess {
      println("Deploy verticel done")
    }
    .await()

//   val vertx = Vertx.vertx()
//   initLogger()
//   initSshClient()
//   initDatabaseConnection()
//   initConfig()
//   val router = initRouter(vertx)
//   initSchedulers(vertx)
//
//   Runtime.getRuntime().addShutdownHook(Thread {
//     sshClient?.close()
//   })
//
//   vertx
//     .createHttpServer()
//     .requestHandler(router)
//     .listen(appConfig.port)
//     .onSuccess {
//       Logger.info("HTTP server started on port ${appConfig.port}", null)
//     }
//     .onFailure {
//       Logger.error("Failed to start service", it)
//     }
}

