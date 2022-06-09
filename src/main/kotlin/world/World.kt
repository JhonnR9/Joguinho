package world

import constants.GameConstants
import constants.GameConstants.Companion.height
import constants.GameConstants.Companion.tileHeight
import constants.GameConstants.Companion.tileSize
import constants.GameConstants.Companion.tileWidth
import constants.GameConstants.Companion.width
import entities.Player
import graphics.Camera
import org.xml.sax.InputSource
import world.json.JsonReading
import java.awt.Graphics
import javax.xml.parsers.SAXParserFactory

class World(path: String, private val camera: Camera, private val player: Player) {
    private val jsonReading =
        JsonReading("C:/Users/jhone/IdeaProjects/Zelda-Clone-with-Kotlin/src/main/resources/maps/lobby.json")
    val map = jsonReading.map
    var entitiesAdd = false

    private val tileset: Tileset = Tileset(16, 16, "Terrain.png")

    fun render(index: Int, graphics: Graphics) {
        val ids = jsonReading.map.layers[index].data
        var xDraw: Int
        var yDraw: Int
        val mapWidth = map.layers[index].width
        val mapHeight = map.layers[index].height
        var indexId = 0
        val layerName: String = map.layers[index].name

        val cameraPositionXStart: Int = camera.x / tileSize
        val cameraPositionYStart: Int = camera.y / tileSize
        var cameraFinalPositionX = cameraPositionXStart + (width / tileSize)
        var cameraFinalPositionY = cameraPositionYStart + (height / tileSize)

        for (y in 0 until mapHeight) {
            for (x in 0 until mapWidth) {

                xDraw = (x * tileWidth)
                yDraw = (y * tileHeight)

                if (!entitiesAdd) {
                    if (layerName == "entities") {
                        when (ids[indexId]) {
                            70 -> {
                                //player
                                player.x = xDraw
                                player.y = yDraw
                                entitiesAdd = true
                            }
                        }
                    }
                }

                if (ids[indexId] != 0 && layerName == "floor_details") {
                    graphics.drawImage(
                        tileset.getTile(ids[indexId]),
                        xDraw - camera.x,
                        yDraw - camera.y,
                        tileWidth,
                        tileHeight,
                        null
                    )
                } else if (ids[indexId] != 0 && layerName == "wall") {
                    graphics.drawImage(
                        tileset.getTile(ids[indexId]),
                        xDraw - camera.x,
                        yDraw - camera.y,
                        tileWidth,
                        tileHeight,
                        null
                    )
                } else if (ids[indexId] != 0 && layerName == "floor") {
                    graphics.drawImage(
                        tileset.getTile(ids[indexId]),
                        xDraw - camera.x,
                        yDraw - camera.y,
                        tileWidth,
                        tileHeight,
                        null
                    )
                }
                indexId++
            }
        }
    }
}



