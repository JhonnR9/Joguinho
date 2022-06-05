
import constants.GameConstants.Companion.gameDimension
import entities.Entity
import entities.Player
import graphics.Spritesheet
import listeners.Keyboard
import world.World
import java.awt.Canvas
import java.awt.Color
import java.awt.Graphics
import java.awt.image.BufferStrategy
import java.awt.image.BufferedImage
import javax.swing.JFrame

class Game: Canvas(), Runnable {
    private val jFrame: JFrame = JFrame()
    private lateinit var thread: Thread
    private var isRunning = true

    private var world = World("lobby")
    private var image: BufferedImage
    private val spritesheet: Spritesheet = Spritesheet("spritesheet.png")
    private var entities: MutableList<Entity> = ArrayList()


    private val spritePlayer = spritesheet.getSprite(1, 1)
    private val player = Player(spritePlayer,spritesheet)
    private val keyboard = Keyboard(player)


    init {
        initFrame()
        image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        entities.add(player)
        addKeyListener(keyboard)
    }


    private fun initFrame() {
        jFrame.title = "JHones"
        jFrame.preferredSize = gameDimension
        jFrame.add(this)
        jFrame.isResizable = false
        jFrame.setLocationRelativeTo(null)
        jFrame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        jFrame.isVisible = true
        jFrame.pack()

    }

    @Synchronized
    fun start() {
        thread = Thread(this)
        isRunning = true
        thread.start()
    }

    @Synchronized
    fun stop() {
        isRunning = false
        thread.join()
    }

    private fun update() {
        for (entity in entities) {
            entity.update()
        }

    }

    private fun clearScreen(graphics: Graphics) {
        graphics.color = Color(150, 75, 0)
        graphics.fillRect(0, 0, width, height)
    }

    private fun createBufferStrategy(): BufferStrategy {
        if (this.bufferStrategy == null) {
            this.createBufferStrategy(3)
        }
        return bufferStrategy

    }

    private fun initializeGraphics(): Graphics {
        return image.graphics
    }

    private fun finishGraphics(g: Graphics, bs: BufferStrategy) {
        g.drawImage(image, 0, 0, width, height, null)
        bs.show()
    }
    private fun showEntities(g:Graphics){
        for (entity in entities) {
            entity.render(g)
        }
    }

    private fun render() {
        val bs = createBufferStrategy()
        var g = initializeGraphics()
        clearScreen(g)
        //render yours objects here
        world.render(g)
        showEntities(g)



        //***//
        g.dispose()
        g = bs.drawGraphics
        finishGraphics(g, bs)
    }

    override fun run() {
        var lastTime = System.nanoTime()
        val amountOfTicks = 60.0
        val ns = 1000000000 / amountOfTicks
        var delta = 0.0
        var frames = 0
       //var timer = System.currentTimeMillis()

        while (isRunning) {
            val now = System.nanoTime()
            delta += (now - lastTime) / ns
            lastTime = now

            if (delta >= 1) {
                update()
                render()
                frames++
                delta--
            }
           /* if (System.currentTimeMillis() - timer >= 1000) {
                frames = 0
                timer = System.currentTimeMillis()
            }*/
        }

        stop()
    }

}
