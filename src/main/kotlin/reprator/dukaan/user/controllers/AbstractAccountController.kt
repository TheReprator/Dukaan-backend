package reprator.dukaan.user.controllers

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.HttpHeaders.Authorization
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import reprator.dukaan.config.ENDPOINT_DEV
import reprator.dukaan.token.AUTH_SCHEME
import reprator.dukaan.token.JWTAuthenticatedUser
import reprator.dukaan.user.AccountInvalidData
import reprator.dukaan.user.controllers.requestEntity.AccountCreationEntity
import reprator.dukaan.user.controllers.responseEntity.AccessTokenModal

abstract class AbstractAccountController : AccountController {

    companion object {
        private const val URL_ACCOUNT_SETUP = "$ENDPOINT_DEV/merchant"
        private const val URL_REFRESH_TOKEN = "$URL_ACCOUNT_SETUP/refreshToken"
        private const val URL_LOGOUT = "$URL_ACCOUNT_SETUP/logout"

        private const val TOKEN_REFRESH = "refreshToken"
        private const val RESPONSE_LOGOUT = "logout successful"
    }

    override fun Route.getRoutes() {

        route(URL_ACCOUNT_SETUP) {
            //Create Account
            post {
                call.respond(Created, create(call.receive<AccountCreationEntity.DTO>()))
            }

            //Update Account or Mobile No
            put {

            }

            //Refresh Token
            authenticate {
                post(URL_REFRESH_TOKEN) {
                    val accessToken = call.request.header(Authorization)!!.substringAfter(AUTH_SCHEME, "none")
                    val authUser = call.authentication.principal<JWTAuthenticatedUser>()!!
                    val refreshToken = call.receiveParameters()[TOKEN_REFRESH] ?: throw AccountInvalidData("Invalid Data")

                    val accessTokenInfo = AccessTokenModal.DTO(accessToken, refreshToken)

                    call.respond(Created, refreshToken(authUser, accessTokenInfo))
                }
            }

            //Logout
            authenticate {
                patch(URL_LOGOUT) {
                    val accessToken = call.request.header(Authorization)!!.substringAfter(AUTH_SCHEME, "none")
                    val authUser = call.authentication.principal<JWTAuthenticatedUser>()!!

                    logout(authUser, accessToken)
                    call.respond(OK, RESPONSE_LOGOUT)
                }
            }
        }

    }
}