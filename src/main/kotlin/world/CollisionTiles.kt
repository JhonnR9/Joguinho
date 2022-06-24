package world

import constants.GameConstants
import constants.GameConstants.Companion.tileWidth
import entities.Player
import world.tiles.Tile
import world.tiles.TileCollider

class CollisionTiles(private val tiles: MutableMap<List<Int>, Tile>) {
    fun isFree(x: Int, y: Int): Boolean {
        val xNext = x / tileWidth
        val yNext = y / tileWidth


        val x1 = xNext
        val y1 = yNext

        val x2 = xNext + 1
        val y2 = yNext

        val x3 = xNext
        val y3 = yNext + 1

        val x4 = xNext + 1
        val y4 = yNext + 1


        return !(tiles[listOf(y1, x1, 2)] is TileCollider ||
                tiles[listOf(y2, x2, 2)] is TileCollider ||
                tiles[listOf(y3, x3, 2)] is TileCollider ||
                tiles[listOf(y4, x4, 2)] is TileCollider)
    }
}