package coroutines.samples.channels

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    val producer = produceNumbers()
    repeat(5) {
        launchProcessor(it, producer)
    }
    delay(950)
    producer.cancel()
}

@ExperimentalCoroutinesApi
private fun CoroutineScope.produceNumbers() = produce {
    var x = 1
    while (true) {
        send(x++)
        delay(100)
    }
}

private fun CoroutineScope.launchProcessor(
        id: Int,
        channel: ReceiveChannel<Int>
) = launch {
    for (message in channel) {
        println("Processor #$id received $message")
    }
}
