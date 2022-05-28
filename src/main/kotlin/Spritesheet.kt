import GameConstants.Companion.tileSize
import java.awt.image.BufferedImage
import java.io.IOException
import javax.imageio.ImageIO

class Spritesheet(path: String) {
      private var spritesheet: BufferedImage = ImageIO.read(javaClass.getResource("sprites/$path"))



    fun Sprite(x: Int, y: Int, width: Int, height: Int): BufferedImage {
        return spritesheet.getSubimage(x, y, width, height)
    }

    fun Sprite(x: Int, y: Int): BufferedImage {
        return spritesheet.getSubimage(x, y, tileSize, tileSize)
    }
}