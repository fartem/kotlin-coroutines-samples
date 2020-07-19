package coroutines.samples.cancellationandtimeouts

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = launch {
        repeat(1_000) {
            println("job: I am sleeping $it...")
            delay(500)
        }
    }
    delay(3_000)
    println("main: I am tired to waiting!")
//    job.cancel()
//    job.join()
    job.cancelAndJoin()
    println("main: Now I can quit!")
}
