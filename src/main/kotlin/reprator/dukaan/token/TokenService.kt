package reprator.dukaan.token

import reprator.dukaan.user.AccessToken
import reprator.dukaan.user.AccountId
import reprator.dukaan.user.ParentOrganization
import reprator.dukaan.user.PhoneNumber

interface TokenService {

    companion object {
        private val tokenExpiration: Milliseconds = Milliseconds((60 * 60 * 1000) * 24)   // 1 day
        private val refreshTokenExpiration: Milliseconds = Milliseconds(((60 * 60 * 1000) * 24) * 10)   // 10 day
    }

    fun generateAccessToken(
        userId: AccountId, phoneNumber: PhoneNumber,
        organizationId: ParentOrganization, expirationPeriod: Milliseconds = tokenExpiration
    ): AccessToken

    fun generateRefreshToken(
        userId: AccountId, phoneNumber: PhoneNumber,
        organizationId: ParentOrganization, expirationPeriod: Milliseconds = refreshTokenExpiration
    ): AccessToken
}
