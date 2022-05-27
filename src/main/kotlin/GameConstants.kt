import java.awt.Dimension
import javax.swing.JFrame

class GameConstants {
    companion object {
        val scale = 1.3
        val width = (640 * scale).toInt()
        val height = (360 * scale).toInt()
        val gameDimension = Dimension(width,height)


    }

}