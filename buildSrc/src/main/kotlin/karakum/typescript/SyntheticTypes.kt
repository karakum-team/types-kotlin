package karakum.typescript

// ConfigProvider

internal const val CONFIG_PROVIDER = "ConfigProvider"

internal const val CONFIG_PROVIDER_BODY = """{
    config?: any;
    error?: Diagnostic;
}"""

internal val CONFIG_PROVIDER_SOURCE = "interface $CONFIG_PROVIDER " +
        CONFIG_PROVIDER_BODY.replace("\n    ", "\n    readonly ")

// RelationCacheSizes

internal const val RELATION_CACHE_SIZES = "RelationCacheSizes"

internal const val RELATION_CACHE_SIZES_BODY = """{
    assignable: number;
    identity: number;
    subtype: number;
    strictSubtype: number;
}"""

internal val RELATION_CACHE_SIZES_SOURCE = "interface $RELATION_CACHE_SIZES " +
        RELATION_CACHE_SIZES_BODY.replace("\n    ", "\n    readonly ")
