package entities

import graphics.Camera
import java.awt.image.BufferedImage

class Enemy(override var x: Int, override var y: Int, override var sprite: BufferedImage,camera: Camera): Entity(x,y, camera) {
}