import java.awt.Dimension
import javax.swing.JFrame

class GameConstants {
    companion object {
        private const val scale = 1.3
        private const val width = (640 * scale).toInt()
        private const val height = (360 * scale).toInt()
        val gameDimension = Dimension(width,height)


    }

}