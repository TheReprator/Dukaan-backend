package reprator.dukaan.user.controllers.requestEntity

import reprator.dukaan.user.ParentOrganization
import reprator.dukaan.user.PhoneNumber
import reprator.dukaan.user.VerificationStatus

interface AccountCreationEntity {
    val phoneNumber: PhoneNumber
    val isVerified: VerificationStatus
    val parentId: ParentOrganization?


    data class DTO(
        override val phoneNumber: PhoneNumber,
        override val isVerified: VerificationStatus,
        override val parentId: ParentOrganization?
    ) : AccountCreationEntity
}