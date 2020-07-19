package coroutines.samples.channels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    val squares = produceSquares()
    squares.consumeEach { square ->
        println(square)
    }
    println("Done")
}

@ExperimentalCoroutinesApi
private fun CoroutineScope.produceSquares(): ReceiveChannel<Int> = produce {
    for (i in 1..5) {
        send(i * i)
    }
}
