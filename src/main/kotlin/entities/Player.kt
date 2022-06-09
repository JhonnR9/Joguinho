package entities

import constants.GameConstants
import constants.GameConstants.Companion.tileHeight
import constants.GameConstants.Companion.tileWidth
import graphics.Animation
import graphics.Camera
import java.awt.Graphics
import java.awt.image.BufferedImage

class Player(
    private val camera: Camera,
    override var x: Int = 0,
    override var y: Int = 0,
    override val width: Int = tileWidth,
    override val height: Int = tileHeight
) : Entity(x, y, width, height) {

    var right = false
    var left = false
    var up = false
    var down = false

    var frames = 0
    var maxFrame = 12
    var index = 0
    var maxIndex = 3


    private val animationSprite = Animation("spritesheet", 9, 9)
    private val animationLeft = animationSprite.getFrames(5, 4)
    private val animationRight = animationSprite.getFrames(14, 4)
    private val animationUp = animationSprite.getFrames(10, 4)
    private val animationDown = animationSprite.getFrames(1, 4)

    private val speed = 2
    var playerFrame: BufferedImage = animationDown[0]


    private fun animationIndex() {
        if (right || left || down || up) {
            frames++
            if (frames == maxFrame) {
                frames = 0
                index++
                if (index > maxIndex) {
                    index = 0
                }
            }
        }
    }

    private fun playerMove() {
        if (right) {
            playerFrame = animationRight[index]
            x += speed

        } else if (left) {
            playerFrame = animationLeft[index]
            x -= speed

        }

        if (down) {
            playerFrame = animationDown[index]
            y += speed

        } else if (up) {
            playerFrame = animationUp[index]
            y -= speed

        }
    }

    override fun update() {
        playerMove()
        animationIndex()
       camera.x =  x-GameConstants.width / 2
        camera.y =  y-GameConstants.height / 2
    }

    override fun render(g: Graphics) {
        g.drawImage(playerFrame, x - camera.x, y - camera.y, width, height, null)

    }
}