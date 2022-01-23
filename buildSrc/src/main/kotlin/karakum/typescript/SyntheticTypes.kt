package karakum.typescript

// NodeFormat

internal object NodeFormat {
    val name = "NodeFormat"
    val alias = "ModuleKind.ESNext | ModuleKind.CommonJS"
    val body = "typealias $name = ModuleKind /* $alias */"
}

// ResolutionMode

internal object ResolutionMode {
    val name = "ResolutionMode"
    val alias = "ModuleKind.CommonJS | ModuleKind.ESNext"
    val body = "typealias $name = ModuleKind /* $alias */"
}

// ConfigProvider

internal const val CONFIG_PROVIDER = "ConfigProvider"

internal const val CONFIG_PROVIDER_BODY = """{
    config?: any;
    error?: Diagnostic;
}"""

internal val CONFIG_PROVIDER_SOURCE = "interface $CONFIG_PROVIDER " +
        CONFIG_PROVIDER_BODY.replace("\n    ", "\n    readonly ")

// OptionsProvider

internal const val OPTIONS_PROVIDER = "OptionsProvider"

internal const val OPTIONS_PROVIDER_BODY = """{
    options: T;
    errors: Diagnostic[];
}"""

internal val OPTIONS_PROVIDER_SOURCE = "interface $OPTIONS_PROVIDER<T extends Any> " +
        OPTIONS_PROVIDER_BODY.replace("\n    ", "\n    readonly ")

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
