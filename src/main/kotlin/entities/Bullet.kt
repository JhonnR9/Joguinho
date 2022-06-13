package entities

import graphics.Camera
import java.awt.image.BufferedImage

class Bullet(
    override var x: Int,
    override var y: Int,
    override val sprite: BufferedImage,
    override val camera: Camera):Entity(x, y, sprite, camera)