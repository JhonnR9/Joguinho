import graphics.Window


class Game : Runnable {

    private lateinit var thread: Thread
    private var isRunning = true
    private val window = Window()


    @Synchronized
    fun start() {
        thread = Thread(this)
        isRunning = true
        thread.start()

    }

    @Synchronized
    fun stop() {
        isRunning = false
        thread.join()
    }

    override fun run() {
        var lastTime = System.nanoTime()
        val amountOfTicks = 60.0
        val ns = 1000000000 / amountOfTicks
        var delta = 0.0
        var frames = 0

        while (isRunning) {
            val now = System.nanoTime()
            delta += (now - lastTime) / ns
            lastTime = now

            if (delta >= 1) {
                window.update()
                window.render()
                frames++
                delta--
            }
        }
        stop()
    }

}
