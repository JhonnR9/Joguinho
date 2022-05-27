import GameConstants.Companion.gameDimension
import java.awt.Canvas
import java.awt.Color
import java.awt.image.BufferedImage
import javax.swing.JFrame

class Game : Canvas(), Runnable {
    var frame: JFrame = JFrame()
    lateinit var thread: Thread
    private var isRunning = true

    private lateinit var image: BufferedImage

    init {
        initFrame()
        image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
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

    @Synchronized
    fun start() {
        thread = Thread(this)
        isRunning = true
        thread.start()
    }

    @Synchronized
    fun stop() {
    }

    private fun update() {

    }

    private fun render() {
        var bs = this.bufferStrategy
        if (bs == null) {
            this.createBufferStrategy(3)
            return
        }
        var graphics = image.graphics
        graphics.color = Color.yellow
        graphics.fillRect(0, 0, width, height)
        graphics = bs.drawGraphics
        graphics.drawImage(image, 0, 0, width, height, null)
        bs.show()

    }

    override fun run() {
        var lastTime = System.nanoTime()
        val amountOfTicks = 60.0
        val ns = 1000000000 / amountOfTicks
        var delta = 0.0
        var frames = 0
        var timer = System.currentTimeMillis()

        while (isRunning) {
            var now = System.nanoTime()
            delta += (now - lastTime) / ns
            lastTime = now

            if (delta >= 1) {
                update()
                render()
                frames++
                delta--
            }
            if (System.currentTimeMillis() - timer >= 1000) {
                println("FPS: $frames")
                frames = 0
                timer = System.currentTimeMillis()
            }
        }
    }

}