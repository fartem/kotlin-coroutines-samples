package coroutines.samples.composingsuspendingfunctions

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlin.system.measureTimeMillis

suspend fun main() {
    val time = measureTimeMillis {
        println("The answer is ${concurrentSum()}")
    }
    println("Completed in $time ms")
}

private suspend fun concurrentSum(): Int = coroutineScope {
    val firstOperand = async {
        firstOperand()
    }
    val secondOperand = async {
        secondOperand()
    }
    firstOperand.await() + secondOperand.await()
}

private suspend fun firstOperand(): Int {
    delay(1_000)
    return 1
}

private suspend fun secondOperand(): Int {
    delay(1_000)
    return 2
}
