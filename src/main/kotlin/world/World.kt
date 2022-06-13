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
    private val tiles: MutableList<Tile> = ArrayList()
    private val entities: MutableList<Entity> = ArrayList()

    val player = Player(0, 0, camera)

    private val tileset: Tileset = Tileset(16, 16, "Terrain.png")

    init {
        createMap()
        entities.add(player)
    }

    private fun createMap() {
        val points = mutableListOf<List<Int>>()

        for (y in 0 until map.height) {
            for (x in 0 until map.width) {
                points.add(listOf(y, x))

            }
        }

        for (layer in map.layers) {
            if (layer.name == "floor") {

                for ((i, point) in points.withIndex()) {
                    if (layer.data[i] != 0) {
                        tiles.add(Tile(point, tileset.getTile(layer.data[i]), camera))
                    }
                }
            }
            if (layer.name == "floor_details") {
                for ((i, point) in points.withIndex()) {
                    if (layer.data[i] != 0) {
                        tiles.add(Tile(point, tileset.getTile(layer.data[i]), camera))
                    }
                }
            }
            if (layer.name == "tile_collider") {
                for ((i, point) in points.withIndex()) {
                    if (layer.data[i] != 0) {
                        tiles.add(TileCollider(point, tileset.getTile(layer.data[i]), camera))
                    }
                }
            }
            if (layer.name == "entities") {
                for ((i, point) in points.withIndex()) {
                    if (layer.data[i] != 0) {
                        addEntities(layer.data[i], point)
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

    private fun isCollider(tile: Tile, graphics: Graphics) {
        if (tile is TileCollider) {
            graphics.color = Color(0, 200, 200, 40)
          //  graphics.fillRect(tile.xDraw - camera.x, tile.yDraw - camera.y, 64, 64)
            if (tile.intersects(player)) {
                if (player.left) {
                    player.x += player.speed
                } else if (player.right) {
                    player.x -= player.speed
                }
                if (player.up) {
                    player.y += player.speed
                } else if (player.down) {
                    player.y -= player.speed
                }
            }
        }
    }


    private fun renderTiles(graphics: Graphics) {
        for (tile in tiles) {
            if (isVisible(tile.xDraw - camera.x, tile.yDraw - camera.y)) {
                if (tile !is TileCollider){
                    tile.render(graphics)
                }

                isCollider(tile, graphics)
            }
        }
    }

    fun render(graphics: Graphics) {
        renderTiles(graphics)
        renderEntities(graphics)

    }
}



