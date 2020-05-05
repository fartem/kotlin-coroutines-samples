package coroutines.samples.basics

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    launch {
        delay(1_000)
        println("--- Coroutine thread ---")
    }
    println("--- Original app thread ---")
}
