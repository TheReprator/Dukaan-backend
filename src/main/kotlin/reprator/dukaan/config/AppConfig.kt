package reprator.dukaan.config

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.util.*

const val SERVER_PORT = 8080

@KtorExperimentalAPI
@EngineAPI
fun setup(): BaseApplicationEngine {
    return server(Netty)
}

@KtorExperimentalAPI
@EngineAPI
fun server(
    engine: ApplicationEngineFactory<BaseApplicationEngine,
            out ApplicationEngine.Configuration>
): BaseApplicationEngine {
    return embeddedServer(
        engine,
        port = SERVER_PORT,
        watchPaths = listOf("mainModule"),
        module = Application::mainModule
    )
}


@KtorExperimentalAPI
fun Application.mainModule() {
    routing {
        get("/") {
            call.respond("Hi Satya")
        }
    }
}