import java.awt.Dimension

class GameConstants {
    companion object {
        private const val scale = 2.5
        private const val width = (320 * scale).toInt()
        private const val height = (180 * scale).toInt()
        val gameDimension = Dimension(width,height)


    }

}