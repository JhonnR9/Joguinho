package listeners

import entities.Player
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class Keyboard(private val player: Player) : KeyListener {
    override fun keyTyped(e: KeyEvent) {

    }

    override fun keyPressed(e: KeyEvent) {
        if (e.keyCode == KeyEvent.VK_RIGHT || e.keyCode == KeyEvent.VK_D) {
            player.right = true
        } else if (e.keyCode == KeyEvent.VK_LEFT || e.keyCode == KeyEvent.VK_A) {
            player.left = true
        }
        if (e.keyCode == KeyEvent.VK_UP || e.keyCode == KeyEvent.VK_W) {
            player.up = true
        } else if (e.keyCode == KeyEvent.VK_DOWN || e.keyCode == KeyEvent.VK_S) {
            player.down = true
        }
    }

    override fun keyReleased(e: KeyEvent) {
        if (e.keyCode == KeyEvent.VK_RIGHT || e.keyCode == KeyEvent.VK_D) {

            player.right = false
        } else if (e.keyCode == KeyEvent.VK_LEFT || e.keyCode == KeyEvent.VK_A) {
            player.left = false
        }
        if (e.keyCode == KeyEvent.VK_UP || e.keyCode == KeyEvent.VK_W) {
            player.up = false
        } else if (e.keyCode == KeyEvent.VK_DOWN || e.keyCode == KeyEvent.VK_S) {
            player.down = false
        }
    }
}