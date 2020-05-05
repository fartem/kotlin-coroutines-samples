package coroutines.samples.basics

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

val job = GlobalScope.launch {
    delay(1_000)
    println("--- Coroutine thread ---")
}

fun main() = runBlocking {
    println("--- Original app thread ---")
    job.join()
}
