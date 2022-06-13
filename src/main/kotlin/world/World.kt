package world

import constants.GameConstants.Companion.height
import constants.GameConstants.Companion.tileHeight
import constants.GameConstants.Companion.tileWidth
import constants.GameConstants.Companion.width
import entities.*
import graphics.Camera
import world.json.JsonReading
import java.awt.Graphics
import java.awt.image.BufferedImage

class World(private val camera: Camera, private val entities: MutableList<Entity>, private val player: Player) {
    private val jsonReading =
        JsonReading("C:/Users/jhone/IdeaProjects/Zelda-Clone-with-Kotlin/src/main/resources/maps/lobby.json")
    val map = jsonReading.map
    var isAddEntities = false

    private val tileset: Tileset = Tileset(16, 16, "Terrain.png")

    private fun drawTile(graphics: Graphics, sprite: BufferedImage, xDraw: Int, yDraw: Int) {
        graphics.drawImage(sprite, xDraw, yDraw, tileWidth, tileHeight, null)
    }

    fun update() {
        camera.x = camera.clamp(player.x - (width / 2), 0, map.width * tileWidth + 16 - width)
        camera.y = camera.clamp(player.y - (height / 2), 0, map.height * tileHeight + 42 - height)
    }

    fun render(index: Int, graphics: Graphics) {
        val ids = jsonReading.map.layers[index].data
        var xDraw: Int
        var yDraw: Int
        val mapWidth = map.layers[index].width
        val mapHeight = map.layers[index].height
        var indexId = 0
        val layerName: String = map.layers[index].name
        var isVisible: Boolean


        for (y in 0 until mapHeight) {
            for (x in 0 until mapWidth) {
                xDraw = (x * tileWidth) - camera.x
                yDraw = (y * tileHeight) - camera.y

                if (layerName == "entities") {
                    if (!isAddEntities) {
                        when (ids[indexId]) {
                            244 -> {
                                //player
                                player.x = xDraw + camera.x
                                player.y = yDraw + camera.y

                            }
                            241 -> {
                                //enemy
                                entities.add(Enemy(xDraw, yDraw, tileset.getTile(ids[indexId]), camera))
                            }
                            242 -> {
                                //life
                                entities.add(LifePacker(xDraw, yDraw, tileset.getTile(ids[indexId]), camera))
                            }
                            243 -> {
                                //bullet
                                entities.add(Bullet(xDraw, yDraw, tileset.getTile(ids[indexId]), camera))
                            }

                        }
                        if (indexId == mapHeight * mapWidth-1) {
                            isAddEntities = true
                        }
                    }
                } else if (ids[indexId] != 0) {
                    isVisible = (xDraw > -tileWidth && yDraw > -tileHeight && xDraw < width && yDraw < height)
                    if (isVisible) {
                        drawTile(graphics, tileset.getTile(ids[indexId]), xDraw, yDraw)
                    }
                }
                indexId++
            }
        }
    }
}



