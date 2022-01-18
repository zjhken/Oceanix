package vip.bluewater.oceanix.server.components

import io.vertx.core.Vertx
import io.vertx.core.http.HttpHeaders
import io.vertx.core.http.HttpMethod
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.BodyHandler
import vip.bluewater.oceanix.server.config.appConfig
import vip.bluewater.oceanix.server.handlers.whoYouAreHandler

fun initRouter(vertx: Vertx): Router {
  val router = Router.router(vertx)

  setupCommonHandlers(router)

  return router
}

fun setupCommonHandlers(router: Router) {
  router.route().failureHandler(::errorCatchHandler)
  router.route().handler(::optionsRequestHandler)
  router.route().handler(::corsHandler)
  router.route().handler(BodyHandler.create())
  router.route().handler(::whoYouAreHandler)
}

fun errorCatchHandler(ctx: RoutingContext) {
  val ex = ctx.failure()
  Logger.error("Error catch in failure handler.", ex)
  ctx.response().setStatusCode(500)
    .end(ex.message)
}

fun optionsRequestHandler(ctx: RoutingContext) {
  if(ctx.request().method() == HttpMethod.OPTIONS) {
    ctx.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, appConfig.allowOrigin)
    ctx.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true")
    ctx.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "POST, GET, OPTIONS")
    // The browser's fetch function will automatically add this Header because it is an asynchronous request
    // hence need to let it pass
    ctx.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "X-Requested-With")
    ctx.response().end()
  }
  else {
    ctx.next()
  }
}

fun corsHandler(ctx: RoutingContext) {
  ctx.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, appConfig.allowOrigin)
  ctx.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true")
  ctx.next()
}
