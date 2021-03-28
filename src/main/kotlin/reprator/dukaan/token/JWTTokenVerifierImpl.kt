package reprator.dukaan.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.JWTVerifier

class JWTTokenVerifierImpl(private val algorithm: Algorithm, private val issuer: String) : JWTTokenVerifier {

    override fun buildVerifier(): JWTVerifier =
        JWT.require(algorithm)
            .withIssuer(issuer)
            .build()
}
/*
val issuer = environment.config.property("jwt.issuer").getString()
    val secret = environment.config.property("jwt.secret").getString()
* */
