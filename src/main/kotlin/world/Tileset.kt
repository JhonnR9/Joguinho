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
        val tilesetMargin = 1
        val tilesetSpacing = 1
        var xTile: Int
        var yTile: Int

        if (!isLoad) {
            for (y in 0 until tilesetHeight) {
                for (x in 0 until tilesetWidth) {
                    xTile = (x * tileSize) + tilesetMargin * x + tilesetSpacing
                    yTile = (y * tileSize) + tilesetMargin * y + tilesetSpacing
                    tiles.add(index, tileFile.getSubimage(xTile, yTile, tileSize, tileSize))
                    index++

                    if (xTile == 137 && yTile == 137) {
                        isLoad = true
                    }

                }
            }
        }

        return tiles[id.toInt()-1]

    }
}