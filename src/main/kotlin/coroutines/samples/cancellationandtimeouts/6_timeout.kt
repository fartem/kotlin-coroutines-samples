package coroutines.samples.cancellationandtimeouts

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

fun main() = runBlocking {
    withTimeout(1_500) {
        repeat(1_000) {
            println("I am sleeping $it...")
            delay(500)
        }
    }
}
