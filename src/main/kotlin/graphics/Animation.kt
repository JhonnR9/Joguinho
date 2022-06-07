package graphics

import constants.GameConstants
import constants.GameConstants.Companion.tileSize
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

class Animation(
    imageName: String,
    private val spritesheetWidth: Int,
    private val spritesheetHeight: Int
) {
    private val frames = mutableListOf<BufferedImage>()
    private var spriteFile: BufferedImage = ImageIO.read(javaClass.getResource("/sprites/$imageName.png"))
    private var isLoad = false

    fun getFrames(starAnimation: Int, sizeAnimation: Int): MutableList<BufferedImage> {
        val framesAnimation = mutableListOf<BufferedImage>()
        var index: Int = 0
        val spritesheetMargin = 1
        val spritesheetSpace = 1
        var xFrame: Int
        var yFrame: Int
        val maxX = (spritesheetWidth * tileSize) + spritesheetWidth - tileSize
        val maxY = (spritesheetHeight * tileSize) + spritesheetHeight - tileSize

        if (!isLoad) {
            for (y in 0 until spritesheetWidth) {
                for (x in 0 until spritesheetHeight) {
                    xFrame = (x * tileSize) + spritesheetMargin * x + spritesheetSpace
                    yFrame = (y * tileSize) + spritesheetMargin * y + spritesheetSpace

                    frames.add(
                        index,
                        spriteFile.getSubimage(xFrame, yFrame, tileSize, tileSize)
                    )
                    index++

                    if (xFrame == maxX && yFrame == maxY) {
                        isLoad = true
                    }
                }
            }
        }
        var frame = starAnimation-1
        for (i in 0 until sizeAnimation) {
            framesAnimation.add(frames[frame])
            frame++
        }
        return framesAnimation
    }
}