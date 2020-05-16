package coroutines.samples.composingsuspendingfunctions

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val time = measureTimeMillis {
        val firstOperand = async(start = CoroutineStart.LAZY) {
            firstOperand()
        }
        val secondOperand = async(start = CoroutineStart.LAZY) {
            secondOperand()
        }
        firstOperand.start()
        secondOperand.start()
        println("The answer is ${firstOperand.await() + secondOperand.await()}")
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
