package coroutines.samples.channels

import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

@ObsoleteCoroutinesApi
fun main() = runBlocking {
    val tickerChannel = ticker(
            delayMillis = 100,
            initialDelayMillis = 0
    )
    var nextElement = withTimeoutOrNull(1) {
        tickerChannel.receive()
    }
    println("Initial element is available immediately: $nextElement")

    nextElement = withTimeoutOrNull(50) {
        tickerChannel.receive()
    }
    println("Next element is ready in 50 ms: $nextElement")

    nextElement = withTimeoutOrNull(60) {
        tickerChannel.receive()
    }
    println("Next element is ready in 60 ms: $nextElement")

    println("Consumer pauses for 150 ms")
    delay(150)

    nextElement = withTimeoutOrNull(1) {
        tickerChannel.receive()
    }
    println("Next element is available immediately after large consumer delay: $nextElement")

    nextElement = withTimeoutOrNull(60) {
        tickerChannel.receive()
    }
    println("Next element is ready in 50 ms after consumer pause in 150 ms: $nextElement")

    tickerChannel.cancel()
}
