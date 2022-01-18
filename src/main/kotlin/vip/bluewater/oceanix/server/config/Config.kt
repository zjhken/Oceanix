package vip.bluewater.oceanix.server.config

import java.util.*

const val envKey = "OCEANIX_ENV"

sealed interface Config {
  val port: Int
  val allowOrigin: String
  val dbConnectStr: String
}

data class DevConfig(
  override val port: Int = 8888,
  override val allowOrigin: String = "http://localhost:3000",
  override val dbConnectStr: String = "jdbc:postgresql://10.0.0.16:55432/oceanix?user=postgres&password=zjhken"
) : Config

data class ProdConfig(
  override val port: Int = 8888,
  override val allowOrigin: String = "http://home.bluewater.vip:20000",
  override val dbConnectStr: String = ""
) : Config

var appConfig: Config = DevConfig()

fun initConfig() {
  val envStr = System.getenv()[envKey]?.lowercase(Locale.ENGLISH) ?: return
  if (envStr == "prod") {
    appConfig = ProdConfig()
  } else {
    error("Illegal environment string. $envKey = $envStr")
  }
}
