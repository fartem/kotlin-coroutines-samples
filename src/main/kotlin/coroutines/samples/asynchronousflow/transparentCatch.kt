package coroutines.samples.asynchronousflow

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

private fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
        println("Emitting $i")
        emit(i)
    }
}

@ExperimentalCoroutinesApi
fun main () = runBlocking {
    simple()
            .catch { e -> println("Caught $e") }
            .collect { value ->
                check(value <= 1) {
                    "Collected $value"
                }
                println(value)
            }
}
