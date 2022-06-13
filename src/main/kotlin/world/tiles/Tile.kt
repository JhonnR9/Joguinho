package world.tiles

import constants.GameConstants.Companion.tileHeight
import constants.GameConstants.Companion.tileWidth
import entities.Player
import graphics.Camera
import java.awt.Graphics
import java.awt.image.BufferedImage

open class Tile(
    point: List<Int>,
    private val sprite: BufferedImage,
    private val camera: Camera
) {
    val xDraw = point[1] * tileWidth
    val yDraw = point[0] * tileHeight
    fun render(graphics: Graphics) {

        graphics.drawImage(
            sprite, xDraw - camera.x, yDraw - camera.y, 64, 64, null
        )
    }
    open fun intersects(p: Player): Boolean {
        var tw: Int = tileWidth
        var th: Int = tileHeight
        var pw = p.width
        var ph = p.height
        if (pw <= 0 || ph <= 0 || tw <= 0 || th <= 0) {
            return false
        }
        val tx: Int = this.xDraw
        val ty: Int = this.yDraw
        val rx = p.x
        val ry = p.y
        pw += rx
        ph += ry
        tw += tx
        th += ty
        //      overflow || intersect
        return (pw < rx || pw > tx) &&
                (ph < ry || ph > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry)
    }
}