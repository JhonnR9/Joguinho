package world

import constants.GameConstants.Companion.height
import constants.GameConstants.Companion.jsonReading
import constants.GameConstants.Companion.tileHeight
import constants.GameConstants.Companion.tileWidth
import constants.GameConstants.Companion.width
import entities.*
import graphics.Camera
import world.tiles.Tile
import world.tiles.TileCollider
import java.awt.Color
import java.awt.Graphics

import kotlin.Boolean as Boolean

class World() {
    private val camera: Camera = Camera()
    private val map = jsonReading.map
    private val entities: MutableList<Entity> = ArrayList()
    private val tiles = mutableMapOf<List<Int>, Tile>()
    private val collisionTiles = CollisionTiles(tiles)
    val player = Player(0, 0, camera, collisionTiles)

    private val tileset: Tileset = Tileset(16, 16, "Terrain.png")

    init {
        createMap()
        entities.add(player)
    }

    private fun createMap() {
        for ((j, layer) in map.layers.withIndex()) {
            if (layer.name == "floor") {
                var i = 0
                for (y in 0 until map.height) {
                    for (x in 0 until map.width) {
                        if (layer.data[i] != 0) {
                            tiles[listOf(y, x, i)] = Tile(listOf(y, x), tileset.getTile(layer.data[i]), camera)
                        }
                        i++

                    }
                }
            }
            if (layer.name == "floor_details") {
                var i = 0
                for (y in 0 until map.height) {
                    for (x in 0 until map.width) {
                        if (layer.data[i] != 0) {
                            tiles[listOf(y, x, j)] = Tile(listOf(y, x), tileset.getTile(layer.data[i]), camera)
                        }
                        i++

                    }
                }
            }
            if (layer.name == "tile_collider") {
                var i = 0
                for (y in 0 until map.height) {
                    for (x in 0 until map.width) {
                        if (layer.data[i] != 0) {
                            tiles[listOf(y, x, j)] = TileCollider(listOf(y, x), tileset.getTile(layer.data[i]), camera)
                        }
                        i++

                    }
                }
            }
            if (layer.name == "entities") {
                var i = 0
                for (y in 0 until map.height) {
                    for (x in 0 until map.width) {
                        if (layer.data[i] != 0) {
                            addEntities(layer.data[i], listOf(y, x))
                        }
                        i++

                    }
                }
            }
        }
    }


    private fun cameraUpdate() {
        camera.x = camera.clamp(player.x - (width / 2), 0, map.width * tileWidth + 16 - width)
        camera.y = camera.clamp(player.y - (height / 2), 0, map.height * tileHeight + 42 - height)
    }


    private fun isVisible(xDraw: Int, yDraw: Int): Boolean {
        return (xDraw > -tileWidth && yDraw > -tileHeight && xDraw < width && yDraw < height)
    }


    private fun addEntities(id: Int, point: List<Int>) {
        val x = point[1] * tileWidth
        val y = point[0] * tileHeight
        val sprite = tileset.getTile(id)
        when (id) {
            244 -> {
                player.x = x + camera.x
                player.y = y + camera.y
            }
            241 -> {
                entities.add(
                    Enemy(x * tileWidth - camera.x, y * tileHeight - camera.y, sprite, camera)
                )
            }
            242 -> {
                entities.add(LifePacker(x, y, sprite, camera))
            }
            243 -> {
                entities.add(Bullet(x, y, sprite, camera))
            }
        }
    }

    private fun updateEntities() {
        for (entity in entities) {
            entity.update()
        }
    }

    private fun renderEntities(graphics: Graphics) {
        for (entity in entities) {
            entity.render(graphics)
        }
    }

    fun update() {
        cameraUpdate()
        updateEntities()

    }

    private fun renderTiles(graphics: Graphics) {
        for (tile in tiles) {
            if (isVisible(tile.value.xDraw - camera.x, tile.value.yDraw - camera.y)) {
                if (tile.value !is TileCollider) {
                    tile.value.render(graphics)
                }
            }
        }
    }

    fun render(graphics: Graphics) {
        renderTiles(graphics)
        renderEntities(graphics)


    }
}



