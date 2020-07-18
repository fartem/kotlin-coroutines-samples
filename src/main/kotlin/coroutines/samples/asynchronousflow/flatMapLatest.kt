package coroutines.samples.asynchronousflow

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.intellij.lang.annotations.Flow

private fun requestFlow(i: Int): Flow<String> = flow {
    emit("$i: First")
    delay(500)
    emit("$i: Second")
}

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    (1..3).asFlow().onEach { delay(100) }
            .collectLatest { value -> requestFlow(value) }
            .collect { value -> println("$value at ${System.currentTimeMillis() - startTime} ms from start") }
    print("Done")
}
