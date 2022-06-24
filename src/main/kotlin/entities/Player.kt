package entities

import constants.GameConstants.Companion.spritesheetSize
import constants.GameConstants.Companion.tileHeight
import constants.GameConstants.Companion.tileWidth
import graphics.Animation
import graphics.Camera
import world.CollisionTiles
import java.awt.Color
import java.awt.Graphics
import java.awt.Rectangle

class Player(
    override var x: Int = 0,
    override var y: Int = 0,
    override val camera: Camera,
    private val collision: CollisionTiles
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

    private val speed = 3

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
        if (right && collision.isFree(x + speed, y)) {
            sprite = animationRight[index]
            x += speed

        } else if (left && collision.isFree(x - speed, y)) {
            sprite = animationLeft[index]
            x -= speed

        }

        if (down && collision.isFree(x, y + speed)) {
            sprite = animationDown[index]
            y += speed

        } else if (up && collision.isFree(x, y - speed)) {
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
     //  g.fillRect(x - camera.x, y - camera.y, width, height)


    }
}