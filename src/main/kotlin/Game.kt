import GameConstants.Companion.gameDimension
import java.awt.Canvas
import javax.swing.JFrame

class Game : Canvas(), Runnable {
    var frame: JFrame = JFrame()

    init {
        initFrame()
    }

    private fun initFrame() {
        frame.preferredSize = gameDimension
        frame.add(this)
        frame.isResizable = false
        frame.setLocationRelativeTo(null)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.isVisible = true
        frame.pack()

    }


    override fun run() {

    }

}