package karakum.typescript

internal const val RELATION_CACHE_SIZES = "RelationCacheSizes"

internal const val RELATION_CACHE_SIZES_BODY = """{
    assignable: number;
    identity: number;
    subtype: number;
    strictSubtype: number;
}"""

internal const val RELATION_CACHE_SIZES_SOURCE = "interface $RELATION_CACHE_SIZES $RELATION_CACHE_SIZES_BODY"
