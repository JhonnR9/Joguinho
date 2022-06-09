package world.json

data class Layer(
    val `data`: List<Int>,
    val height: Int,
    val id: Int,
    val name: String,
    val opacity: Int,
    val type: String,
    val visible: Boolean,
    val width: Int,
    val x: Int,
    val y: Int
)