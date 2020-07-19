package coroutines.samples.channels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    var cur = numbersFrom(2)
    repeat(10) {
        val prime = cur.receive()
        println(prime)
        cur = filter(cur, prime)
    }
    coroutineContext.cancelChildren()
}

@ExperimentalCoroutinesApi
private fun  CoroutineScope.numbersFrom(
        start: Int
) = produce {
    var x = start
    while (true) {
        send(x++)
    }
}

@ExperimentalCoroutinesApi
private fun CoroutineScope.filter(
        numbers: ReceiveChannel<Int>,
        prime: Int
) = produce {
    for (number in numbers) {
        if (number % prime != 0) {
            send(number)
        }
    }
}
