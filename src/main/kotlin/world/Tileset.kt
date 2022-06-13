package world

import constants.GameConstants.Companion.tileSize
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

class Tileset( tilesetWidth: Int,tilesetHeight: Int, fileName: String) {
    private var tileFile: BufferedImage = ImageIO.read(javaClass.getResource("/tilesets/$fileName"))
    private val tiles = mutableListOf<BufferedImage>()

    init {
        var index = 0
        val tilesetMargin = 0
        val tilesetSpacing = 0
        var xTile: Int
        var yTile: Int
        for (y in 0 until tilesetHeight) {
            for (x in 0 until tilesetWidth) {
                xTile = (x * tileSize) + tilesetMargin * x + tilesetSpacing
                yTile = (y * tileSize) + tilesetMargin * y + tilesetSpacing
                tiles.add(index, tileFile.getSubimage(xTile, yTile, tileSize, tileSize))
                index++
            }
        }
    }

    fun getTile(id: Int): BufferedImage = tiles[id-1]

}