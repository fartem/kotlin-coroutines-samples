package coroutines.samples.asynchronousflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

private fun simple(): Flow<Int> = flow {
    println("Flow started")
    for (i in 1..3) {
        delay(100)
        emit(i)
    }
}

fun main() = runBlocking {
    println("Calling simple()...")
    val flow = simple()
    println("Calling flow.collect()...")
    flow.collect { value ->
        println(value)
    }
    println("Calling flow.collect() again...")
    flow.collect { value ->
        println(value)
    }
}
