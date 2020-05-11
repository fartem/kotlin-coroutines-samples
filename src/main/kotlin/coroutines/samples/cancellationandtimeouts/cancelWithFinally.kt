package coroutines.samples.cancellationandtimeouts

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    var job = launch {
        try {
            repeat(1_000) {
                println("job: I am sleeping ${it}...")
                delay(500)
            }
        } finally {
            println("job: I am running finally!")
        }
    }
    delay(1_500)
    println("main: I am tired of waiting!")
    job.cancelAndJoin()
    println("main: Now I can quit!")
}
