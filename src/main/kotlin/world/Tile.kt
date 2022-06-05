package world

import java.awt.Graphics
import java.awt.Rectangle
import java.awt.image.BufferedImage

class Tile(private val tileX: kotlin.Double, val tileY: kotlin.Double, spriteTile: BufferedImage): Rectangle() {
    override fun getX(): kotlin.Double {
        return tileX
    }

    override fun getY(): kotlin.Double {
        return tileY
    }
    fun render(g: Graphics){

    }
}