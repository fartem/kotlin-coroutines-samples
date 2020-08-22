package coroutines.samples.sharedmutablestateandconcurrency

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

@ObsoleteCoroutinesApi
private val counterContext = newSingleThreadContext("CounterContext")
private var counter = 0

@ObsoleteCoroutinesApi
fun main() = runBlocking {
    withContext(counterContext) {
        massiveRun {
            counter++
        }
    }
    println("counter = $counter")
}

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
