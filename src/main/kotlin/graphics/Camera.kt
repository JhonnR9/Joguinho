package graphics

class Camera {
    var x = 0
    var y = 0

    fun clamp(currentX: Int, xMin: Int, xMax: Int): Int {
        var clampX = currentX
        if (currentX < xMin) {
            clampX = xMin
        }
        if (currentX > xMax){
            clampX=xMax
        }
            return clampX
    }
}