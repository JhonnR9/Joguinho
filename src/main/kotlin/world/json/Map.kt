package world.json

data class Map(
    val compressionlevel: Int,
    val height: Int,
    val infinite: Boolean,
    val layers: List<Layer>,
    val nextlayerid: Int,
    val nextobjectid: Int,
    val orientation: String,
    val renderorder: String,
    val tiledversion: String,
    val tileheight: Int,
    val tilesets: List<Tileset>,
    val tilewidth: Int,
    val type: String,
    val version: String,
    val width: Int
)