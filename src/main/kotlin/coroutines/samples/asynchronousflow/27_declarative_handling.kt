package coroutines.samples.asynchronousflow

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

private fun simple(): Flow<Int> = flow {
    emit(1)
    throw RuntimeException()
}

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    simple()
            .onCompletion { cause -> if (cause != null) println("Flow completed exceptionally") }
            .catch { _ -> println("Caught exception") }
            .collect { value -> println(value) }
}
