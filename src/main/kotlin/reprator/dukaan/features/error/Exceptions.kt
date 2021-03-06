package reprator.dukaan.features.error

import io.ktor.http.HttpStatusCode
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.Forbidden
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.http.HttpStatusCode.Companion.Unauthorized

open class StatusCodeException(val statusCode: HttpStatusCode, message: String? = null, cause: Throwable? = null) : Exception(message, cause) {
    open class NotFound(message: String? = null, cause: Throwable? = null) : StatusCodeException(NotFound, message, cause)
    open class BadRequest(message: String? = null, cause: Throwable? = null) : StatusCodeException(BadRequest, message, cause)
    open class UnAuthenticationException(message: String? = null, cause: Throwable? = null) : StatusCodeException(Unauthorized, message, cause)
    open class UnAuthorizedUser(message: String? = null, cause: Throwable? = null) : StatusCodeException(Forbidden, message, cause)
}