package reprator.dukaan.user.controllers.responseEntity

import reprator.dukaan.user.AccessToken
import reprator.dukaan.user.controllers.requestEntity.AccessTokenEntity

interface AccessTokenModal : AccessTokenEntity {

    data class DTO(
        override val accessToken: AccessToken,
        override val refreshToken: AccessToken
    ) : AccessTokenModal

}