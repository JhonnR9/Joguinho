package graphics

import constants.GameConstants.Companion.spritesheetSize
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

class Animation(
    imageName: String,
    private val spritesheetWidth: Int,
    private val spritesheetHeight: Int
    , private val margin: Int, private val space: Int
) {
    private val frames = mutableListOf<BufferedImage>()
    private var spriteFile: BufferedImage = ImageIO.read(javaClass.getResource("/sprites/$imageName.png"))
    private var isLoad = false

    fun getFrames(starAnimation: Int, sizeAnimation: Int): MutableList<BufferedImage> {
        val framesAnimation = mutableListOf<BufferedImage>()
        var index = 0
        var xFrame: Int
        var yFrame: Int
        val maxX = (spritesheetWidth * spritesheetSize)+ spritesheetHeight * space - spritesheetSize
        val maxY = (spritesheetHeight * spritesheetSize) + spritesheetHeight * space - spritesheetSize

        if (!isLoad) {
            for (y in 0 until spritesheetHeight) {
                for (x in 0 until spritesheetWidth ) {
                    xFrame = (x * spritesheetSize) + margin * x + space
                    yFrame = (y * spritesheetSize) + margin * y + space

                     frames.add(index,spriteFile.getSubimage(xFrame, yFrame, spritesheetSize, spritesheetSize))
                   // println("yFrame: $yFrame, xFrame: $xFrame")

                    index++

                    if (xFrame == maxX && yFrame == maxY) {
                        isLoad = true
                    }
                }
            }
        }

        var currentFrame = starAnimation - 1
        for (i in 0 until sizeAnimation) {
                 framesAnimation.add(frames[currentFrame])
            currentFrame++
        }

         return framesAnimation
    }
}