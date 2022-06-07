package world

import constants.GameConstants.Companion.tileHeight
import constants.GameConstants.Companion.tileSize
import constants.GameConstants.Companion.tileWidth
import org.xml.sax.InputSource
import java.awt.Color
import java.awt.Color.blue
import java.awt.Graphics
import java.awt.Rectangle
import javax.xml.parsers.SAXParserFactory

class World(path: String) {
    private val loadMapXml: LoadMapXml = LoadMapXml()
    private var ids: List<String>
    private val tileset: Tileset = Tileset(9, 9, "tileset.png")


    init {
        val parser = SAXParserFactory.newInstance().newSAXParser()
        val input = InputSource(javaClass.getResourceAsStream("/maps/$path.xml"))

        parser.parse(input, loadMapXml)
        ids = loadMapXml.stringMap.toString().split(',')


    }

    fun render(graphics: Graphics) {
        var xDraw: Int
        var yDraw: Int
        val mapWidth = 20
        val mapHeight = 20
        var id: String
        var indexId = 0


        for (y in 0 until mapHeight) {
            for (x in 0 until mapWidth) {
                xDraw = (x * tileWidth)
                yDraw = (y * tileHeight)
                id = ids[indexId].replace("\\s".toRegex(), "")
                graphics.drawImage(tileset.getTile(id), xDraw, yDraw, tileWidth, tileHeight, null)
             //   if (id == "12") {
                  //  graphics.color = Color(0,0,250,50)
                   // graphics.drawRect(xDraw,yDraw,tileWidth, tileHeight)
               // }


                indexId++
            }
        }


    }


}