package io.github.eendroroy.bootstrap.config

import io.github.eendroroy.bootstrap.filter.RequestLoggingFilter
import io.github.eendroroy.bootstrap.filter.Slf4jMdcFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered

@Configuration
class FilterRegistrations {
    @Bean
    fun slf4jMdcFilterRegistration(): FilterRegistrationBean<Slf4jMdcFilter> {
        val registrationBean = FilterRegistrationBean<Slf4jMdcFilter>()
        registrationBean.filter = Slf4jMdcFilter()
        registrationBean.order = Ordered.HIGHEST_PRECEDENCE
        return registrationBean
    }

    @Bean
    fun statsLoggingFilterFilterRegistration(): FilterRegistrationBean<RequestLoggingFilter> {
        val registrationBean = FilterRegistrationBean<RequestLoggingFilter>()
        registrationBean.filter = RequestLoggingFilter()
        registrationBean.order = Ordered.HIGHEST_PRECEDENCE + 1
        return registrationBean
    }
}
