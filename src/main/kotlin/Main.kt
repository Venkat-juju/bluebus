//import com.bluebus.views.HomeScreenView
import javafx.application.Application
import javafx.application.Application.launch
import kotlinx.coroutines.*
import kotlin.concurrent.thread

suspend fun printWorld() = coroutineScope{
    delay(4000)
    launch {
        printWorld1()
    }

    launch {
        printWorld2()
    }
    println("world")
}

suspend fun printWorld1() {
    delay(2000)
    println("World 1")
}

suspend fun printWorld2() {
    delay(1000)
    println("World 2")
}




fun main() = runBlocking {
    //HomeScreenView().showMainOption()

    repeat(100_000) { // launch a lot of coroutines
        launch {
            //Thread.sleep(5000L)
            delay(5000)
            print(".")
        }
    }
    // github token: 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
}