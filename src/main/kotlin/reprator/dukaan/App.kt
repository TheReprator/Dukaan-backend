package reprator.dukaan

import io.ktor.server.engine.*
import reprator.dukaan.config.setup

@OptIn(EngineAPI::class)
fun main() {
    setup().start()
}
