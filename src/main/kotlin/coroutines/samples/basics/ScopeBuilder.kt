package coroutines.samples.basics

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    launch {
        delay(1_000)
        println("--- runBlocking task ---")
    }

    coroutineScope {
        launch {
            delay(2_000)
            println("--- Nested launch task ---")
        }

        delay(100)
        println("--- Coroutine scope task ---")
    }

    println("--- Coroutine scope is over ---")
}
