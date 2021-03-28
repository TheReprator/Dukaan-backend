package reprator.dukaan.user

import reprator.dukaan.features.error.StatusCodeException

class AccountInvalidData(message: String? = null, cause: Throwable? = null) : StatusCodeException.BadRequest(message, cause)
class AccountInvalidAuthorization(message: String? = null, cause: Throwable? = null) : StatusCodeException.UnAuthorizedUser(message, cause)
