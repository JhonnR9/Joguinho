package entities

import java.awt.Graphics
import java.awt.image.BufferedImage

open class Entity(
    open var x: Int,
    open var y: Int,
    open val width: Int,
    open val height: Int
) {


    open fun update() {

    }

    open fun render(g: Graphics) {

    }
}