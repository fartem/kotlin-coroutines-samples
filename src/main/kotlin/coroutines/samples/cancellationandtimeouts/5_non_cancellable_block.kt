package coroutines.samples.cancellationandtimeouts

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch {
        try {
            repeat(1_000) {
                println("job: I am sleeping $it...")
                delay(500)
            }
        } finally {
            withContext(NonCancellable) {
                println("job: I am running finally!")
                delay(1_000)
                println("job: And I have just delayed for 1 sec because I am non-cancellable!")
            }
        }
    }
    delay(1_500)
    println("main: I am tired of waiting!")
    job.cancelAndJoin()
    println("main: Now I can quit!")
}
