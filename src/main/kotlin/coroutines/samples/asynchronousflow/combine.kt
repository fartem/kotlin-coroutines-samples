package coroutines.samples.asynchronousflow

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    val strings = flowOf("one", "two", "three")
    val startTime = System.currentTimeMillis()
    (1..3).asFlow()
            .onEach { delay(300) }
            .combine(strings) { a, b -> "$a - $b" }
            .collect { value -> println("$value at ${System.currentTimeMillis() - startTime} ms from start") }
}
