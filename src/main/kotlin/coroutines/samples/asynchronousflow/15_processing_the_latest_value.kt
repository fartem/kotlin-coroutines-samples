package coroutines.samples.asynchronousflow

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
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
        simple().collectLatest { value ->
            println("Collecting $value")
            delay(300)
            println("Done $value")
        }
    }
    println("Collected in $time ms")
}
