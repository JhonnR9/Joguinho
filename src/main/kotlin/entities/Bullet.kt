package entities

import graphics.Camera
import java.awt.image.BufferedImage

class Bullet(
    override var x: Int,
    override var y: Int,
    override var sprite: BufferedImage,
    override val camera: Camera):Entity(x, y, camera)