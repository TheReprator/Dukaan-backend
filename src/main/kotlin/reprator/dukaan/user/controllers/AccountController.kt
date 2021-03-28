package reprator.dukaan.user.controllers

import org.kodein.di.ktor.controller.DIController
import reprator.dukaan.token.JWTAuthenticatedUser
import reprator.dukaan.user.AccessToken
import reprator.dukaan.user.controllers.requestEntity.AccessTokenEntity
import reprator.dukaan.user.controllers.requestEntity.AccountCreationEntity
import reprator.dukaan.user.controllers.responseEntity.AccessTokenModal
import reprator.dukaan.user.controllers.responseEntity.AccountCreationModal

interface AccountController : DIController {

    suspend fun create(info: AccountCreationEntity): AccountCreationModal

    suspend fun refreshToken(
        authenticatedUser: JWTAuthenticatedUser,
        info: AccessTokenEntity
    ): AccessTokenModal

    suspend fun logout(
        authenticatedUser: JWTAuthenticatedUser,
        accessToken: AccessToken,
        isLogout: Boolean = true
    )
}