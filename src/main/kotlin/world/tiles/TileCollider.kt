package world.tiles

import graphics.Camera
import world.tiles.Tile
import java.awt.image.BufferedImage

class TileCollider(
    point: List<Int>,
    sprite: BufferedImage,
    camera: Camera
) : Tile(point, sprite, camera) {

}