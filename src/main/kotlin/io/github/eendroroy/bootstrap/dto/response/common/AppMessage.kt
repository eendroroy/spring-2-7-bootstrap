package io.github.eendroroy.bootstrap.dto.response.common

import io.github.eendroroy.bootstrap.util.Jsonable

data class AppMessage(
    var message: String? = null,
    var details: String? = null,
    var type: String? = null,
) : Jsonable
