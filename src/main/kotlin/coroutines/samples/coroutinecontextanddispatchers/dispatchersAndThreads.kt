package coroutines.samples.coroutinecontextanddispatchers

import kotlinx.coroutines.*

fun main() = runBlocking {
    launch {
        println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Unconfined) {
        println("Unconfined launch: I'm working in thread ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Default) {
        println("Default: I'm working in thread ${Thread.currentThread().name}")
    }
    launch(newSingleThreadContext("MyOwnThread")) {
        println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
    }
    return@runBlocking
}
