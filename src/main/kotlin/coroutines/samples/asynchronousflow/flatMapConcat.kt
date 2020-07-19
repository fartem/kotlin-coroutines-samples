package coroutines.samples.asynchronousflow

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

private fun requestFlow(i: Int): Flow<String> = flow {
    emit("$i: First")
    delay(500)
    emit("$i: Second")
}

@FlowPreview
fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    (1..3).asFlow().onEach { delay(100) }
            .flatMapConcat { value -> requestFlow(value) }
            .collect { value -> println("$value at ${System.currentTimeMillis() - startTime} ms from start") }
    print("Done")
}
