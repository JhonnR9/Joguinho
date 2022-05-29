package graphics

import constants.GameConstants.Companion.tileSize
import entities.Entity
import java.awt.image.BufferedImage


class Animation(spritesheet: Spritesheet, initialX: Int, initialY: Int) {


    private val sprite1 =
        spritesheet.sprite((tileSize * initialX + 1) + initialX, (initialY * tileSize) + initialY + 1)

    private val sprite2 =
        spritesheet.sprite((tileSize * (initialX+1)) + initialX + 2, (initialY * tileSize) + initialY + 1)
    private val sprite3 =
        spritesheet.sprite((tileSize * (initialX + 2)) + initialX + 3, (initialY * tileSize) + initialY + 1)
    private val sprite4 =
        spritesheet.sprite((tileSize * (initialX + 3)) + initialX + 4, (initialY * tileSize) + initialY + 1)
    var animation = arrayOf(sprite1, sprite2, sprite3, sprite4)

}