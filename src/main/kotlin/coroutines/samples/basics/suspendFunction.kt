package coroutines.samples.basics

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    launch {
        doInLaunch()
    }
    println("--- main task ---")
}

suspend fun doInLaunch() {
    delay(1_000)
    println("--- doInLaunch task ---")
}
