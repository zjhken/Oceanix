package vip.bluewater.oceanix.server.handlers

import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.coroutines.await
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import vip.bluewater.oceanix.server.entity.User

val coroutineScope = CoroutineScope(Dispatchers.IO)
val coroutineScope2 = CoroutineScope(Dispatchers.Unconfined)

// AuthN
fun whoYouAreHandler(ctx: RoutingContext) {
  coroutineScope.launch{
    val cookies = ctx.request().cookies("name")
    if (cookies.size > 0) {
      val tokenCookie = cookies.take(1)[0]
      val user: User = getUserFromLoginByToken(tokenCookie.value)
    }

  }
}

fun getUserFromLoginByToken(token: String?): User {
  TODO("Not yet implemented")
}

// AuthZ
fun whatYouCanDoHandler(ctx: RoutingContext) {

}

