package entities

import constants.GameConstants.Companion.spritesheetSize
import constants.GameConstants.Companion.tileHeight
import constants.GameConstants.Companion.tileWidth
import graphics.Animation
import graphics.Camera
import java.awt.Color
import java.awt.Graphics
import java.awt.Rectangle

class Player(
    override var x: Int = 0,
    override var y: Int = 0,
    override val camera: Camera,
) : Entity(x, y, camera) {
    override var width = spritesheetSize
    override val height = spritesheetSize

    var right = false
    var left = false
    var up = false
    var down = false


    var frames = 0
    var maxFrame = 12
    var index = 0
    var maxIndex = 8


    private val animationSprite = Animation("SpritesheetMan", 9, 4, 0, 0)
    private val animationLeft = animationSprite.getFrames(10, 9)
    private val animationRight = animationSprite.getFrames(28, 9)
    private val animationUp = animationSprite.getFrames(1, 9)
    private val animationDown = animationSprite.getFrames(19, 9)

    val speed = 5
    var collider: Boolean = false

    init {
        sprite = animationDown[0]
    }


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
        if (right ) {
            sprite = animationRight[index]
            x += speed

        } else if (left ) {
            sprite = animationLeft[index]
            x -= speed

        }

        if (down ) {
            sprite = animationDown[index]
            y += speed

        } else if (up ) {
            sprite = animationUp[index]
            y -= speed

        }
    }

    override fun update() {
        playerMove()
        animationIndex()

    }


    override fun render(g: Graphics) {
        g.drawImage(sprite, x - camera.x, y - camera.y, width, height, null)
        g.color = Color(250, 0, 0, 70)
       // g.fillRect(x - camera.x, y - camera.y, width, height)


    }
}