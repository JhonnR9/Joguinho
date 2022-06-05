package world

import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import java.lang.StringBuffer


class LoadMapXml : DefaultHandler() {
    var stringMap = StringBuffer()


    override fun startDocument() {

    }

    override fun endDocument() {

    }


    override fun startElement(uri: String, localName: String, tag: String, attributes: Attributes) {

    }


    override fun endElement(uri: String, localName: String, tag: String) {


    }

    override fun characters(ch: CharArray, start: Int, length: Int) {
        stringMap.append(ch,start,length)


    }
}

