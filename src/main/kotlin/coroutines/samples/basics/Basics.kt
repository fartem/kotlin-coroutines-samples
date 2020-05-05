package coroutines.samples.basics

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    GlobalScope.launch {
        println("--- Coroutine thread ---")
        delay(1_000) // Non-blocking delay
    }
    println("--- Original app thread ---")
//    Thread.sleep(2_000) // Blocking delay

//    runBlocking { // Blocking delay
//        delay(2_000)
//    }

    delay(2_000) // Blocking delay
}
