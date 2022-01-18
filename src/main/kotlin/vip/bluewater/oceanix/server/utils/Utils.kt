package vip.bluewater.oceanix.server.utils

inline fun <T> timeCostReturns(block: () -> T): Pair<T, Long> {
  val start = System.currentTimeMillis()
  val result = block()
  return result to (System.currentTimeMillis() - start)
}
