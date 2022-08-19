package karakum.query

import karakum.common.Suppress
import karakum.common.fileSuppress

internal const val QUERY_KEY = "QueryKey"

// language=Kotlin
internal val QUERY_KEY_BODY = """
// $GENERATOR_COMMENT    

${fileSuppress(Suppress.NOTHING_TO_INLINE)}

${Package.CORE.pkg}

inline fun <T : QueryKey> QueryKey(
    vararg keys: Any,
): T = keys.unsafeCast<T>()
"""
