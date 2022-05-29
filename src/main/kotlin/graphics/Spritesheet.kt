package graphics

import constants.GameConstants.Companion.tileSize
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

class Spritesheet(path: String) {
      private var spritesheet: BufferedImage = ImageIO.read(javaClass.getResource("/sprites/$path"))


    fun sprite(x: Int, y: Int, width: Int, height: Int): BufferedImage {
        return spritesheet.getSubimage(x, y, width, height)
    }

    fun sprite(x: Int, y: Int): BufferedImage {
        return spritesheet.getSubimage(x, y, tileSize, tileSize)
    }
}