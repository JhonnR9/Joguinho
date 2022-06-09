package world

import constants.GameConstants.Companion.tileSize
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

class Tileset(private val tilesetWidth: Int, private val tilesetHeight: Int, fileName: String) {
    private var tileFile: BufferedImage = ImageIO.read(javaClass.getResource("/tilesets/$fileName"))
    private val tiles = mutableListOf<BufferedImage>()

    private var isLoad = false

    fun getTile(id: String): BufferedImage {
        var index: Int = 0
        val tilesetMargin = 0
        val tilesetSpacing = 0
        var xTile: Int
        var yTile: Int

        val maxX = (tilesetWidth * tileSize) + tilesetWidth * tilesetMargin - tileSize
        val maxY = (tilesetHeight * tileSize) + tilesetHeight * tilesetMargin - tileSize

        if (!isLoad) {
            for (y in 0 until tilesetHeight) {
                for (x in 0 until tilesetWidth) {
                    xTile = (x * tileSize) + tilesetMargin * x + tilesetSpacing
                    yTile = (y * tileSize) + tilesetMargin * y + tilesetSpacing
                    tiles.add(index, tileFile.getSubimage(xTile, yTile, tileSize, tileSize))
                    index++

                    if (xTile == maxX && yTile == maxY) {
                        isLoad = true
                    }

                }
            }
        }

        return tiles[id.toInt() - 1]

    }
}