package coroutines.samples.exceptionhandling

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import java.lang.AssertionError

fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }
    supervisorScope {
        val child = launch(handler) {
            val child = launch(handler) {
                println("The child throws an exception")
                throw AssertionError()
            }
            println("The scope is completing")
            child.join()
        }
        child.join()
    }
    println("The scope is completed")
}
