package entities

import constants.GameConstants.Companion.spritesheetSize
import graphics.Camera
import java.awt.Graphics
import java.awt.image.BufferedImage

open class Entity(
    open var x: Int,
    open var y: Int,
    open val sprite: BufferedImage,
    open val camera: Camera
) {
    open val width: Int = spritesheetSize
    open val height: Int = spritesheetSize
    open fun update() {

    }

    open fun render(g: Graphics) {
        g.drawImage(sprite, x - camera.x, y - camera.y, width, height, null)
    }
}