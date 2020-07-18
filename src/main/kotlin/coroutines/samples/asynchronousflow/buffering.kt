package coroutines.samples.asynchronousflow

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

private fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100)
        println("Emitting $i")
        emit(i)
    }
}

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    val time = measureTimeMillis {
        simple()
                .buffer()
                .collect { value ->
                    delay(300)
                    println(value)
                }
    }
    println("Collected in $time ms")
}
