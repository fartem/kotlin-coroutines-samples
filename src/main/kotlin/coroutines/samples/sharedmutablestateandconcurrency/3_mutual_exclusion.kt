package coroutines.samples.sharedmutablestateandconcurrency

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.system.measureTimeMillis

private var mutex = Mutex()
private var counter = 0

fun main() = runBlocking {
    withContext(Dispatchers.Default) {
        massiveRun {
            mutex.withLock {
                counter++
            }
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
