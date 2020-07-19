package coroutines.samples.basics

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    repeat(1_000_000 + 1) { iteration ->
        launch {
            delay(100)
            println(iteration)
        }
    }
}
