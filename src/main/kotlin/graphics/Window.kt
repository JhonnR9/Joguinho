package graphics

import constants.GameConstants
import entities.Entity
import entities.Player
import listeners.Keyboard
import world.World
import java.awt.*
import javax.swing.JFrame

class Window : Canvas() {
    private val jFrame: JFrame = JFrame()
    private val world = World("lobby")
    private val entities: MutableList<Entity> = ArrayList()
    private val player = Player()
    private val keyboard = Keyboard(player)
    //  val layer = MapJson()

    init {
        initFrame()
        entities.add(player)
        addKeyListener(keyboard)
        createBufferStrategy(3)
    }

    private fun initFrame() {
        val icon: Image = Toolkit.getDefaultToolkit().getImage(javaClass.getResource("/icon/icon.png"))
        jFrame.apply {
            title = "Rpg Game"
            iconImage = icon
            preferredSize = GameConstants.gameDimension
            add(this@Window)
            isResizable = false
            defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            isVisible = true
            pack()
            setLocationRelativeTo(null)

        }

    }

    private fun clearScreen(graphics: Graphics) {
        graphics.apply {
            color = Color.black
            fillRect(0, 0, width, height)
        }
    }

    private fun updateEntities() {
        for (entity in entities) {
            entity.update()
        }
    }

    private fun showEntities(graphics: Graphics) {
        for (entity in entities) {
            entity.render(graphics)
        }
    }

    fun update() {
        updateEntities()
    }

    fun render() {
        bufferStrategy.drawGraphics.apply {
            clearScreen(this)
            /** render yours objects here **/
            world.render(this)
            showEntities(this)


            /**----------------------------**/
            dispose()
            bufferStrategy.show()

        }
    }
}