package reprator.dukaan.user.controllers.requestEntity

import reprator.dukaan.user.AccessToken

interface AccessTokenEntity {
    val accessToken: AccessToken
    val refreshToken: AccessToken

    data class DTO(
        override val accessToken: AccessToken,
        override val refreshToken: AccessToken

    ) : AccessTokenEntity
}
