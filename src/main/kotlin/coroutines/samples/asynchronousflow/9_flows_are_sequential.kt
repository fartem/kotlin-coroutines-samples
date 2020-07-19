package coroutines.samples.asynchronousflow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    (1..5).asFlow()
            .filter { value ->
                println("Filter $value")
                value % 2 == 0
            }
            .map { value ->
                println("Map $value")
            }
            .collect { value -> println("Collect $value") }
}
