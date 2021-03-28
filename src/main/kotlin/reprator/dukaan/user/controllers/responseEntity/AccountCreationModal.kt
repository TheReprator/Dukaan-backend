package reprator.dukaan.user.controllers.responseEntity

import reprator.dukaan.user.AccountId
import reprator.dukaan.user.ParentOrganization
import reprator.dukaan.user.PhoneNumber
import reprator.dukaan.user.VerificationStatus
import reprator.dukaan.user.controllers.requestEntity.AccessTokenEntity
import reprator.dukaan.user.controllers.requestEntity.AccountCreationEntity

interface AccountCreationModal : AccountCreationEntity {
    val id: AccountId

    val accessTokenInfo: AccessTokenEntity

    data class DTO(
        override val id: AccountId,
        override val phoneNumber: PhoneNumber,
        override val isVerified: VerificationStatus,
        override val parentId: ParentOrganization,

        override val accessTokenInfo: AccessTokenEntity
    ) : AccountCreationModal
}