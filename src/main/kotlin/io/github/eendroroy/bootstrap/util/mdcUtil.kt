package io.github.eendroroy.bootstrap.util

import io.github.eendroroy.bootstrap.config.Constant
import org.slf4j.MDC
import java.util.UUID

fun generateCorrelationId() = UUID.randomUUID().toString().uppercase()

fun getMdcId(): String? = MDC.get(Constant.MDC_LOG_ID)
fun getOrGenerateMdcId(): String = getMdcId() ?: generateCorrelationId()

fun setMdcId() = MDC.put(Constant.MDC_LOG_ID, generateCorrelationId())
fun setMdcId(id: String) = MDC.put(Constant.MDC_LOG_ID, id)

fun setMdcIdIfNotNull() {
    if (getMdcId() == null) {
        MDC.put(Constant.MDC_LOG_ID, generateCorrelationId())
    }
}

fun setMdcIdIfNotNull(id: String) {
    if (getMdcId() == null) {
        MDC.put(Constant.MDC_LOG_ID, id)
    }
}

fun removeMdcId() = MDC.remove(Constant.MDC_LOG_ID)
