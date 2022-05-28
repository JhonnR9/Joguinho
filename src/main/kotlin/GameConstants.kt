import java.awt.Dimension

class GameConstants {
    companion object {
        const val scale = 2.5
        const val width = (320 * scale).toInt()
        const val height = (160 * scale).toInt()
        const val tileSize = 16
        val gameDimension = Dimension(width,height)


    }

}