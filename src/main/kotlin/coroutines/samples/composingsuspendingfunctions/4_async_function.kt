package coroutines.samples.composingsuspendingfunctions

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() {
    val time = measureTimeMillis {
        val firstOperand = firstOperandAsync()
        val secondOperand = secondOperandAsync()
        runBlocking {
            println("The answer is ${firstOperand.await() + secondOperand.await()}")
        }
    }
    println("Completed in $time ms")
}

private fun firstOperandAsync() = GlobalScope.async {
    delay(1_000)
    return@async 1
}

private fun secondOperandAsync() = GlobalScope.async {
    delay(1_000)
    return@async 2
}
