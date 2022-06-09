package world.json

import kotlin.jvm.JvmStatic
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader

class JsonReading(path: String) {
    private val gson = Gson()
    private val file = File(path)
    private val reader = JsonReader(FileReader(file))
    val map: Map = gson.fromJson(reader, Map::class.java)
}
