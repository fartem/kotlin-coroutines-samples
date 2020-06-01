package coroutines.samples.coroutinecontextanddispatchers

import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() = runBlocking {
    newSingleThreadContext("Ctx1").use { ctx1 ->
        newSingleThreadContext("Ctx2").use { ctx2 ->
            runBlocking(ctx1) {
                log("Started in Ctx1")
                withContext(ctx2) {
                    log("Working in Ctx2")
                }
                log("Back to Ctx1")
            }
        }
    }
    return@runBlocking
}

private fun log(message: String) = println("[${Thread.currentThread().name}] $message")
