package io.github.eendroroy.bootstrap.filter

import io.github.eendroroy.bootstrap.config.Constant
import io.github.eendroroy.bootstrap.util.generateCorrelationId
import io.github.eendroroy.bootstrap.util.removeMdcId
import io.github.eendroroy.bootstrap.util.setMdcId
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class Slf4jMdcFilter : OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        try {
            val token = generateCorrelationId()
            setMdcId(token)
            response.addHeader(Constant.MDC_LOG_ID, token)
            chain.doFilter(request, response)
        } finally {
            removeMdcId()
        }
    }
}
