package coroutines.samples.asynchronousflow

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.intellij.lang.annotations.Flow

private fun numbers(): Flow<Int> = flow {
    try {
        emit(1)
        emit(2)
        println("This line will not execute")
        emit(3)
    } finally {
        println("Finally in numbers")
    }
}

fun main() = runBlocking {
    numbers()
            .take(2)
            .collect { value -> println(value) }
    println("Done")
}
