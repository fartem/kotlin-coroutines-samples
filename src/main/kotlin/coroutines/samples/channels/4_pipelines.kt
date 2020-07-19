package coroutines.samples.channels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    val numbers = produceNumbers()
    val squares = square(numbers)
    repeat(5) {
        println(squares.receive())
    }
    println("Done")
    coroutineContext.cancelChildren()
}

@ExperimentalCoroutinesApi
private fun CoroutineScope.produceNumbers() = produce {
    var x = 1
    while (true) {
        send(x++)
    }
}

@ExperimentalCoroutinesApi
private fun CoroutineScope.square(
        numbers: ReceiveChannel<Int>
): ReceiveChannel<Int> = produce {
    for (number in numbers) {
        send(number * number)
    }
}
