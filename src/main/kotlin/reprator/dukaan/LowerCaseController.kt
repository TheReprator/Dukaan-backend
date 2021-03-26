package reprator.dukaan

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.DI
import org.kodein.di.ktor.controller.DIController
import org.kodein.di.ktor.di

//https://github.com/Kodein-Framework/Kodein-Samples/blob/master/di/standalone/ktor/src/main/kotlin/KodeinApplication.kt
//https://gist.github.com/romainbsl/b21c4536a33548292edf033347c4e66c
//https://github.com/jmcshane/ktor-kodein-sample/blob/master/src/main/kotlin/org/jetbrains/ktor/samples/json/PrometheusMonitor.kt

class LowerCaseController(application: Application) : DIController {
    override val di: DI by di { application }

    override fun Route.getRoutes() {
        authenticate("basicAuth") {
            route("/protected") {
                get("/user/lower") {
                    val principal = call.principal<UserIdPrincipal>()!!
                    call.respondText("Hello ${principal.name.toLowerCase()}!")
                }
            }
        }
    }
}

/**
 * Base controller interface to leverage your Ktor server as a MVC-like architecture
 *
 * Example:
 * class DIControllerImpl(application: Application) : DIController(application) {
 *    override val di by di { application }
 *    override fun Route.getRoutes() {
 *      route(ROUTE_VERSION) {
 *      get {
 *        val version: String by instance("version")
 *        call.respondText(version)
 *      }
 *    }
 * }
 *
 */