package coroutines.samples.asynchronousflow

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    val strings = flowOf("one", "two", "three")
    (1..3).asFlow()
            .zip(strings) { a, b -> "$a - $b" }
            .collect { value -> println(value) }
}
