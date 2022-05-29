package entities

import java.awt.image.BufferedImage

class Player(
    private val sprite: BufferedImage,
    private val x: Int,
    private val y: Int,
    private val width: Int = 48,
    private val height: Int = 48
) : Entity(sprite, x, y, width, height) {

}