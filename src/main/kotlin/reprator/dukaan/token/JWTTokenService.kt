package reprator.dukaan.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import reprator.dukaan.user.AccessToken
import reprator.dukaan.user.AccountId
import reprator.dukaan.user.ParentOrganization
import reprator.dukaan.user.PhoneNumber
import java.util.*

inline class Milliseconds(val expirationPeriod: Long)

class JWTTokenService(
    private val algorithm: Algorithm,
    private val issuer: String
) : TokenService {

    companion object {
        const val CLAIM_USER_PHONE = "userPhoneNumber"
        const val CLAIM_USER_ORGANIZATION_ID = "userOrganizationId"
        const val CLAIM_USER_ID = "userId"
    }

    private fun generateToken(
        userId: AccountId,
        phoneNumber: PhoneNumber,
        organizationId: ParentOrganization,
        expirationPeriod: Milliseconds
    ): String = JWT.create()
        .withSubject("Authentication")
        .withIssuer(issuer)
        .withAudience()
        .withClaim(CLAIM_USER_ID, userId.accountId)
        .withClaim(CLAIM_USER_PHONE, phoneNumber.phoneNumber)
        .withClaim(CLAIM_USER_ORGANIZATION_ID, organizationId.parentOrganization)
        .withExpiresAt(obtainExpirationDate(expirationPeriod))
        .sign(algorithm)

    private fun obtainExpirationDate(expirationPeriod: Milliseconds) = Date(
        System.currentTimeMillis() +
                expirationPeriod.expirationPeriod
    )

    override fun generateAccessToken(
        userId: AccountId,
        phoneNumber: PhoneNumber,
        organizationId: ParentOrganization,
        expirationPeriod: Milliseconds
    ): AccessToken = AccessToken(generateToken(userId, phoneNumber,
        organizationId, expirationPeriod))

    override fun generateRefreshToken(
        userId: AccountId,
        phoneNumber: PhoneNumber,
        organizationId: ParentOrganization,
        expirationPeriod: Milliseconds
    ): AccessToken = AccessToken(generateToken(userId, phoneNumber,
        organizationId, expirationPeriod))
}
