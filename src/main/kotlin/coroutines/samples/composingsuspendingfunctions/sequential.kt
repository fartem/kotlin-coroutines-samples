package coroutines.samples.composingsuspendingfunctions

import kotlinx.coroutines.delay
import kotlin.system.measureTimeMillis

suspend fun main() {
    val time = measureTimeMillis {
        println("The answer is ${firstOperand() + secondOperand()}")
    }
    println("Completed in $time ms")
}

private suspend fun firstOperand(): Int {
    delay(1_000)
    return 1
}

private suspend fun secondOperand(): Int {
    delay(1_000)
    return 2
}
