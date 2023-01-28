package karakum.query

import karakum.common.GENERATOR_COMMENT
import karakum.common.Suppress
import karakum.common.fileSuppress

internal const val QUERY_KEY = "QueryKey"

// language=Kotlin
internal val QUERY_KEY_BODY = """
// $GENERATOR_COMMENT    

${fileSuppress(Suppress.NOTHING_TO_INLINE)}

${Package.CORE.pkg}

import js.core.ReadonlyArray

inline fun <T : QueryKey> QueryKey(
    vararg keys: Comparable<*>,
): T =
    keys.unsafeCast<T>()

fun <T : QueryKey> QueryKey(
    parentKey: QueryKey,
    vararg keys: Comparable<*>,
): T =
    (parentKey.unsafeCast<ReadonlyArray<*>>() + keys).unsafeCast<T>()
"""
