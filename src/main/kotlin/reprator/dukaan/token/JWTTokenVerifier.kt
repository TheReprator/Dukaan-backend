package reprator.dukaan.token

import com.auth0.jwt.interfaces.JWTVerifier

interface JWTTokenVerifier {
    fun buildVerifier(): JWTVerifier
}
