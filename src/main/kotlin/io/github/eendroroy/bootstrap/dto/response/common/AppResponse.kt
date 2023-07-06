package io.github.eendroroy.bootstrap.dto.response.common

import io.github.eendroroy.bootstrap.util.Jsonable
import org.springframework.http.HttpStatus

data class AppResponse<T>(
    var data: T,
    var pageInfo: AppPageInfo? = null,
    var success: Boolean = true,
    var code: Int = HttpStatus.OK.value(),
    var status: String = HttpStatus.OK.reasonPhrase,
    var errorMessages: List<AppMessage> = emptyList(),
) : Jsonable
