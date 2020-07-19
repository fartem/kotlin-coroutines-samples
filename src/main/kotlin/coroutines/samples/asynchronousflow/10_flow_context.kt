package coroutines.samples.asynchronousflow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

private fun simple(): Flow<Int> = flow {
    log("Started simple flow")
    for (i in 1..3) {
        emit(i)
    }
}

private fun log(message: String) = println("[${Thread.currentThread().name}] $message")

fun main() = runBlocking {
    simple().collect { value -> log("Collected $value") }
}
