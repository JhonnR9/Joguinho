package constants

import java.awt.Dimension
import java.awt.Toolkit

class GameConstants {

    companion object {
        private val screen: Dimension = Toolkit.getDefaultToolkit().screenSize
        val width = (screen.width )/2
        val height = (screen.height)/2
        const val tileSize = 16
        const val spritesheetSize = 64
        const val tileWidth = 64
        const val tileHeight = 64
        val gameDimension = Dimension(width, height)


    }

}