package coroutines.samples.channels

import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val channel = Channel<String>()
    launch {
        sendString(channel, "foo", 200)
    }
    launch {
        sendString(channel, "BAR!", 500)
    }
    repeat(6) {
        println(channel.receive())
    }
    coroutineContext.cancelChildren()
}

private suspend fun sendString(
        channel: SendChannel<String>,
        string: String,
        time: Long
) {
    while (true) {
        delay(time)
        channel.send(string)
    }
}
