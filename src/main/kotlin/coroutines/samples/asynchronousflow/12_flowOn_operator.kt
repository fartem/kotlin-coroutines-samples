package coroutines.samples.asynchronousflow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
private fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
        Thread.sleep(100)
        log("Emitting $i")
        emit(i)
    }
}.flowOn(Dispatchers.Default)

private fun log(message: String) = println("[${Thread.currentThread().name}] $message")

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    simple().collect { value -> log("Collect $value") }
}
