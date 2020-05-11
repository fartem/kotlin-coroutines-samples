package coroutines.samples.cancellationandtimeouts

import kotlinx.coroutines.*

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5) {
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I am sleeping ${i++}...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1_500)
    println("main: I am tired of waiting!")
    job.cancelAndJoin()
    println("main: Now I can quit!")
}
