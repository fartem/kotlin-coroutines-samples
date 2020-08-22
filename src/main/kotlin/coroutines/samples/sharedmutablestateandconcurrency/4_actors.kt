package coroutines.samples.sharedmutablestateandconcurrency

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor
import kotlin.system.measureTimeMillis

@ObsoleteCoroutinesApi
fun main() = runBlocking<Unit> {
    val counter = counterActor()
    withContext(Dispatchers.Default) {
        massiveRun {
            counter.send(IncrementCounter)
        }
    }
    val response = CompletableDeferred<Int>()
    counter.send(GetCounter(response))
    println("counter = ${response.await()}")
    counter.close()
}

sealed class CounterMessage
object IncrementCounter : CounterMessage()
class GetCounter(val response: CompletableDeferred<Int>) : CounterMessage()

private suspend fun massiveRun(action: suspend () -> Unit) {
    val n = 100
    val k = 1_000
    val time = measureTimeMillis {
        coroutineScope {
            repeat(n) {
                launch {
                    repeat(k) {
                        action()
                    }
                }
            }
        }
    }
    println("Completed ${n * k} actions in $time ms")
}

@ObsoleteCoroutinesApi
fun CoroutineScope.counterActor() = actor<CounterMessage> {
    var counter = 0
    for (message in channel) {
        when (message) {
            is IncrementCounter -> counter++
            is GetCounter -> message.response.complete(counter)
        }
    }
}
