package graphics

import constants.GameConstants.Companion.tileSize
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

class Spritesheet(fileName: String) {
      private var spritesheet: BufferedImage = ImageIO.read(javaClass.getResource("/sprites/$fileName"))


    fun getSprite(x: Int, y: Int, width: Int, height: Int): BufferedImage {
        return spritesheet.getSubimage(x, y, width, height)
    }

    fun getSprite(x: Int, y: Int): BufferedImage {
        return spritesheet.getSubimage(x, y, tileSize, tileSize)
    }
}