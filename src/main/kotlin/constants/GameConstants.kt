package constants

import java.awt.Dimension
import java.awt.Toolkit

class GameConstants {

    companion object {
        private val screen: Dimension = Toolkit.getDefaultToolkit().screenSize
        private const val scale = 0.6
        private  val width = (screen.width* scale).toInt()
        val height = (screen.height* scale).toInt()
        const val tileSize = 16
        const val tileWidth = 64
        const val tileHeight = 64
        val gameDimension = Dimension(width, height)


    }

}