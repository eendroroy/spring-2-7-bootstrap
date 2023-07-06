package io.github.eendroroy.bootstrap.dto.response

import io.github.eendroroy.bootstrap.util.Jsonable

data class HelloResponse(
    var message: String
): Jsonable
