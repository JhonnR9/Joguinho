package entities

import graphics.Animation
import graphics.Spritesheet
import java.awt.Graphics
import java.awt.image.BufferedImage

class Player(
    private val sprite: BufferedImage,
    val spritesheet: Spritesheet,
    override var x: Int = 50,
    override var y: Int = 50,
    override val width: Int = 64,
    override val height: Int = 64
) : Entity(sprite, x, y, width, height) {


    var right = false
    var left = false
    var up = false
    var down = false

    var frames = 0
    var maxFrame = 12
    var index = 0
    var maxIndex = 3
    var moved = false

    private val animationLeft = Animation(spritesheet, 1, 1)
    private val animationRight = Animation(spritesheet, 5, 1)
    private val animationUp = Animation(spritesheet, 1, 2)
    private val animationDown = Animation(spritesheet, 5, 2)

    private var dir: String = "left"
    private val speed = 2
    override fun update() {
        moved = false
        if (right) {
            moved = true
            x += speed
        } else if (left) {
            moved = true
            x -= speed
        }
        if (down) {
            moved = true
            y += speed
        } else if (up) {
            moved = true
            y -= speed
        }
        if (moved) {
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

    override fun render(g: Graphics) {
        if (right) {
            g.drawImage(animationLeft.animation[index], x, y, width, height, null)
            dir = "left"
        } else if (left) {
            g.drawImage(animationRight.animation[index], x, y, width, height, null)
            dir = "right"
        }
        if (up) {
            g.drawImage(animationUp.animation[index], x, y, width, height, null)
            dir = "up"
        } else if (down) {
            g.drawImage(animationDown.animation[index], x, y, width, height, null)
            dir = "down"
        }
        if (!right || !left || !up || !down) {
            if (dir == "left") {
                g.drawImage(animationLeft.animation[index], x, y, width, height, null)
            } else if (dir == "right") {
                g.drawImage(animationRight.animation[index], x, y, width, height, null)
            }
            if (dir == "up") {
                g.drawImage(animationUp.animation[index], x, y, width, height, null)
            } else if (dir == "down") {
                g.drawImage(animationDown.animation[index], x, y, width, height, null)
            }
        }
    }
}