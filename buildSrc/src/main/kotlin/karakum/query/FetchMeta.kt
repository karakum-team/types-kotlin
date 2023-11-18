package karakum.query

// language=TypeScript
internal val FETCH_META_SOURCE = """
interface FetchMeta {
    fetchMore?: {
        direction: FetchDirection;
    };
}
""".trimIndent()

// language=kotlin
internal val FETCH_META_CODE = """
sealed external interface FetchMeta {
    var fetchMore: FetchMore?
    
    sealed interface FetchMore {
        var direction: FetchDirection
    }
}    
""".trimIndent()
