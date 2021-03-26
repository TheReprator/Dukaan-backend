package reprator.dukaan.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.util.DefaultIndenter
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.util.*
import reprator.dukaan.features.error.ErrorFeature

const val SERVER_PORT = 8080
private const val ENDPOINT_DEV = "/api/v1/accounts"

const val ACCOUNTS_ENDPOINT_ = ENDPOINT_DEV


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

    //Error Handling
    install(ErrorFeature)

    //Jackson for content negotiation
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT) // Pretty Prints the JSON

            configure(SerializationFeature.INDENT_OUTPUT, true)
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            setSerializationInclusion(JsonInclude.Include.NON_NULL)

            setDefaultPrettyPrinter(DefaultPrettyPrinter().apply {
                indentArraysWith(DefaultPrettyPrinter.FixedSpaceIndenter.instance)
                indentObjectsWith(DefaultIndenter("  ", "\n"))
            })
        }
    }

    //Accept json as headers
    install(DefaultHeaders) {
        header(HttpHeaders.Accept, ContentType.Application.Json.toString())
    }

    routing {
        get("/") {
            call.respond("Hi Satya")
        }
    }
}