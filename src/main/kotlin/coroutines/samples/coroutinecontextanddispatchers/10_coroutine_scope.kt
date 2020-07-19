package coroutines.samples.coroutinecontextanddispatchers

import kotlinx.coroutines.*

fun main() = runBlocking{
    val activity = Activity()
    activity.doSomething()
    println("Launched coroutines")
    delay(500)
    println("Destroying activity!")
    activity.destroy()
    delay(1_000)
}

class Activity {

    private val mainScope = MainScope()

    fun doSomething() {
        repeat(10) { i ->
            mainScope.launch {
                delay((i + 1) * 200L)
                println("Coroutine $i is done")
            }
        }
    }

    fun destroy() {
        mainScope.cancel()
    }

}
