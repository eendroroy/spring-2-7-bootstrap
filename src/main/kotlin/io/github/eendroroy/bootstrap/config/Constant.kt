package io.github.eendroroy.bootstrap.config

object Constant {
    val SENSITIVE_PARAM_KEYS = listOf(
        "password", "login", "token", "pin", "authorization",
        "newPassword", "confirmPassword", "currentPassword",
    )

    const val TEXT_SENSITIVE = "____SENSITIVE_DATA____"

    const val MDC_LOG_ID = "Log-ID"
}
