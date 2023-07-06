package io.github.eendroroy.bootstrap.util

import io.github.eendroroy.bootstrap.config.Constant

fun Map<String, Array<String>?>.censoredArrayMap(): MutableMap<String, Array<String>?> = HashMap(this).apply {
    Constant.SENSITIVE_PARAM_KEYS.forEach { param: String ->
        this[param]?.let { this[param] = arrayOf(Constant.TEXT_SENSITIVE) }
    }
}

fun Map<String, List<String>?>.censoredListMap(): MutableMap<String, List<String>?> = HashMap(this).apply {
    Constant.SENSITIVE_PARAM_KEYS.forEach { param: String ->
        this[param]?.let { this[param] = listOf(Constant.TEXT_SENSITIVE) }
    }
}

fun Map<String, Array<String>?>.asListMap(): HashMap<String, List<String>?> {
    return HashMap<String, List<String>?>().also {
        HashMap(this).entries.forEach { item ->
            it[item.key] = item.value?.asList() ?: emptyList()
        }
    }
}

fun Map<String, List<String>?>.asArrayMap(): HashMap<String, Array<String>?> {
    return HashMap<String, Array<String>?>().also {
        HashMap(this).entries.forEach { item ->
            it[item.key] = item.value?.toTypedArray() ?: emptyArray()
        }
    }
}
