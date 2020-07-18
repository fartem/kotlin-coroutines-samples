package coroutines.samples.asynchronousflow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

private fun simple(): Flow<String> = flow {
    for (i in 1..3) {
        println("Emitting $i")
        emit(i)
    }
}.map { value ->
    check(value <= 1) { "Chared on $value" }
    "string $value"
}

fun main () = runBlocking {
    try {
        simple().collect { value -> println(value) }
    } catch (e: Throwable) {
        println("Caught $e")
    }
}
