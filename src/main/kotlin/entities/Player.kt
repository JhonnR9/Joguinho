package entities

import java.awt.image.BufferedImage

class Player(
    private val sprite: BufferedImage,
    override var x: Int = 0,
    override var y: Int = 0,
    private val width: Int = 48,
    private val height: Int = 48
) : Entity(sprite, x, y, width, height) {
     var right = false
     var left = false
     var up = false
     var down = false
     private val speed = 2
    override fun update() {
        if (right) {
            x += speed
        } else if (left) {
            x -= speed
        }
        if (down) {
            y += speed
        } else if (up) {
            y -= speed
        }
    }
}