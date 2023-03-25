import java.util.concurrent.Exchanger
import kotlin.random.Random

object PingPong {
    @JvmStatic
    fun main(args: Array<String>) {
        val exchanger = Exchanger<String>()
        val alice = Thread(PingPongRunnable("Alice", "Ping", exchanger, "\u001b[31m"))
        val bob = Thread(PingPongRunnable("Bob", "Pong", exchanger, "\u001b[34m"))
        alice.start()
        bob.start()
    }
}

internal class PingPongRunnable(
    private val name: String,
    private var message: String,
    private val exchanger: Exchanger<String>,
    private val color: String
) : Runnable {
    private val colorReset = "\u001b[0m"
    private val colorName: String
        get() = "$color$name$colorReset"
    override fun run() {
        for (i in 0..2) {
            println("Iteration: $i $colorName has $message")
            try {
                println("Iteration: $i $colorName going to sleep.")
                Thread.sleep(Random.nextLong(0, 5000))
                println("Iteration: $i $colorName ready to exchange.")
                message = exchanger.exchange(message)
                println("Iteration: $i $colorName $message exchange completed")
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }
}
