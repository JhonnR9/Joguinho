package entities

import java.awt.Graphics
import java.awt.image.BufferedImage

open class Entity(
    private val sprite: BufferedImage,
    private val x: Int,
    private val y: Int,
    private val width: Int ,
    private val height: Int) {

    fun update() {

    }

    fun render(g: Graphics) {
        g.drawImage(sprite, x, y, width, height, null)
    }
}