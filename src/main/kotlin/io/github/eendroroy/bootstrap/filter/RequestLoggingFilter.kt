package io.github.eendroroy.bootstrap.filter

import io.github.eendroroy.bootstrap.config.Loggers
import io.github.eendroroy.bootstrap.util.logRequestStats
import io.github.eendroroy.bootstrap.util.logResponseStats
import org.slf4j.MDC
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class RequestLoggingFilter : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        var exception: Exception? = null

        try {
            logRequestStats(request)
            filterChain.doFilter(request, response)
        } catch (ex: Exception) {
            exception = ex
            throw ex
        } finally {
            logResponseStats(response, exception)
            Loggers.STAT_LOGGER.info("Log MDC items")
            MDC.clear()
        }
    }
}
