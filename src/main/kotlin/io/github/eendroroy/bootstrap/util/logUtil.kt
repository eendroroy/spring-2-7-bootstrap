package io.github.eendroroy.bootstrap.util

import io.github.eendroroy.bootstrap.config.CustomHeaders
import org.slf4j.MDC
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

fun logRequestStats(request: HttpServletRequest) {
    val params = request.getQueryParameters().censoredArrayMap()
    MDC.put(CustomHeaders.APP_VERSION, request.getHeader(CustomHeaders.APP_VERSION))
    MDC.put(CustomHeaders.APP_NAME, request.getHeader(CustomHeaders.APP_NAME))
    MDC.put(CustomHeaders.APP_DEVICE_ID, request.getHeader(CustomHeaders.APP_DEVICE_ID))
    MDC.put(CustomHeaders.APP_DEVICE_IMEI, request.getHeader(CustomHeaders.APP_DEVICE_IMEI))
    MDC.put(CustomHeaders.APP_DEVICE_MAC_ADDRESS, request.getHeader(CustomHeaders.APP_DEVICE_MAC_ADDRESS))
    MDC.put(CustomHeaders.APP_DEVICE_VENDOR, request.getHeader(CustomHeaders.APP_DEVICE_VENDOR))
    MDC.put(CustomHeaders.APP_DEVICE_MODEL, request.getHeader(CustomHeaders.APP_DEVICE_MODEL))
    MDC.put(CustomHeaders.LATITUDE, request.getHeader(CustomHeaders.LATITUDE))
    MDC.put(CustomHeaders.LONGITUDE, request.getHeader(CustomHeaders.LONGITUDE))
    MDC.put(CustomHeaders.CORRELATION_ID, request.getHeader(CustomHeaders.CORRELATION_ID))
    MDC.put("startTime", LocalDateTime.now().format(formatter))
    MDC.put("requestParams", params.toJson())
    MDC.put("requestPath", request.method + "-" + request.requestURL.toString())
}

fun logResponseStats(response: HttpServletResponse, ex: Exception?) {
    MDC.put("endTime", LocalDateTime.now().format(formatter))
    MDC.put("executeTime", durationSeconds(MDC.get("startTime"), MDC.get("endTime")).toString())
    MDC.put("responseStatus", response.status.toString())
    ex?.let { MDC.put("exception", ex::class.qualifiedName) }
}

fun HttpServletRequest.getQueryParameters(): Map<String, Array<String>> {
    return if (this.queryString.isNullOrEmpty()) emptyMap()
    else URLDecoder.decode(this.queryString, StandardCharsets.UTF_8.toString())
        .split("&").map {
            val parts = it.split("=", limit = 2)
            mapOf(parts.first() to parts.last())
        }
        .flatMap { it.entries }
        .groupBy { it.key }
        .mapValues { entry -> entry.value.map { it.value }.toTypedArray() }
}

private fun durationSeconds(start: String?, end: String?): Double {
    if (start == null || end == null) return Double.NaN
    val duration = Duration.between(LocalDateTime.parse(start, formatter), LocalDateTime.parse(end, formatter))
    return duration.toMillis() / 1_000.0
}

private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
