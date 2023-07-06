package io.github.eendroroy.bootstrap.util

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper

fun Jsonable.toJson(throws: Boolean = false) = process(this, throws)

fun Map<String, Any?>.toJson() = process(this)

private fun process(any: Any, throws: Boolean = false) = try {
    objectMapper.findAndRegisterModules().writeValueAsString(any)
} catch (e: JsonProcessingException) {
    if (throws) throw e else null
}

private val objectMapper = ObjectMapper().findAndRegisterModules()
