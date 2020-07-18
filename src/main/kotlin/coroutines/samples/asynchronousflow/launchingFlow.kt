package coroutines.samples.asynchronousflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

private fun events(): Flow<Int> = (1..3).asFlow().onEach { delay(100) }

fun main() = runBlocking {
    events().onEach { event ->
        println("Event: $event")
    }.collect()
    println("Done")
}
