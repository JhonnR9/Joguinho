package entities

import graphics.Camera
import java.awt.image.BufferedImage

class Enemy(override var x: Int, override var y: Int, override val sprite: BufferedImage,camera: Camera): Entity(x,y,sprite, camera) {
}