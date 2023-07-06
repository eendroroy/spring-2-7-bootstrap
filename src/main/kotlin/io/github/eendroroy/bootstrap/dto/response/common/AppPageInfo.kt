package io.github.eendroroy.bootstrap.dto.response.common

import io.github.eendroroy.bootstrap.util.Jsonable

data class AppPageInfo(
    var totalElements: Long,
    var totalPages: Int,
    var currentPage: Int,
    var pageSize: Int,
) : Jsonable
