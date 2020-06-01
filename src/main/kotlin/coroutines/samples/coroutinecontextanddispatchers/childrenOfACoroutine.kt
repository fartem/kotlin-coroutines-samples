package coroutines.samples.coroutinecontextanddispatchers

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val request = launch {
        GlobalScope.launch {
            println("job1: I run in GlobalScope and execute independently!")
            delay(1_000)
            println("job1: I am not affected by cancellation of the request")
        }
        launch {
            delay(100)
            println("job2: I am a child of the request coroutine")
            delay(1_000)
            println("job2: I will no execute this line if my parent request in cancelled")
        }
    }
    delay(500)
    request.cancel()
    delay(1_000)
    println("main: Who has survived request cancellation?")
}
