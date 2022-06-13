package graphics

import constants.GameConstants
import entities.Entity
import entities.Player
import listeners.Keyboard
import world.World
import java.awt.*
import java.awt.image.BufferedImage
import javax.swing.JFrame

class Window : Canvas() {
    private val jFrame: JFrame = JFrame()
    private val entities: MutableList<Entity> = ArrayList()
    private val camera: Camera = Camera()
    private val player = Player(BufferedImage(32,32, BufferedImage.TYPE_INT_RGB),0,0,camera)
    private val world = World( camera, entities,player)
    private val keyboard = Keyboard(player)

    init {
        initFrame()
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
            isResizable = true
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
        world.update()
        player.update()
    }

    private fun renderWorld(graphics: Graphics) {
        val layersSize = world.map.layers.size
        for (i in 0 until layersSize) {
            world.render(i, graphics)
        }
    }

    fun render() {
        bufferStrategy.drawGraphics.apply {
            clearScreen(this)
            /** render yours objects here **/
            renderWorld(this)
            showEntities(this)
            player.render(this)

            /**----------------------------**/
            dispose()
            bufferStrategy.show()

        }
    }
}