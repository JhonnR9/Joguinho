import com.sun.source.tree.WhileLoopTree
import java.awt.Canvas
import java.awt.Dimension
import java.util.*
import javax.swing.JFrame

fun main(args: Array<String>) {
    val game = Game()
    val frame = JFrame()

    with(frame) {
        add(game)
        title = "Joguinho"
        pack()
        setLocationRelativeTo(null)
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        isVisible = true
    }

    Thread(game).start()


}

class Game : Canvas(), Runnable {
    companion object {
        val WIDTH = 640
        val HEIGHT = 360
        val SCALE = 1.5
        val dimension = Dimension((WIDTH * SCALE).toInt(), (HEIGHT * SCALE).toInt())
    }

    init {
        this.preferredSize = dimension
    }

    override fun run() {
        while (true){
            println("Chamando Game Looping!!")
        }
    }
}