package coroutines.samples.basics

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    GlobalScope.launch {
        repeat(1_000) { iteration ->
            delay(100)
            println(iteration)
        }
    }
    Thread.sleep(3_000)
}
