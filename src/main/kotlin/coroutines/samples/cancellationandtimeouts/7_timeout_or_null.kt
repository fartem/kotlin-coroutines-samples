package coroutines.samples.cancellationandtimeouts

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

fun main() = runBlocking {
    val result = withTimeoutOrNull(1_500) {
        repeat(1_000) {
            println("I am sleeping $it...")
            delay(500)
        }
        "Done"
    }
    println("Result is $result")
}
