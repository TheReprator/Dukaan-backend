package reprator.dukaan.token

import io.ktor.auth.*
import reprator.dukaan.user.AccountId
import reprator.dukaan.user.ParentOrganization
import reprator.dukaan.user.PhoneNumber

const val AUTH_SCHEME = "Bearer"
data class JWTAuthenticatedUser(val userId: AccountId, val phoneNumber: PhoneNumber,
                                val organizationId: ParentOrganization) : Principal