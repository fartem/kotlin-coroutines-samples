package coroutines.samples.asynchronousflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

private suspend fun performRequest(request: Int): String {
    delay(1000)
    return "response $request"
}

fun main() = runBlocking {
    (1..3).asFlow()
            .map { request -> performRequest(request) }
            .collect { response -> println(response) }
    println("Done")
}
