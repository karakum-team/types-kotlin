package karakum.typescript

import karakum.common.sealedUnionBody
import karakum.common.unionBody

internal data class Union(
    val name: String,
    val source: String,
) {
    val body: String
        get() = sealedUnionBody(
            name = name,
            values = source
                .splitToSequence(" | ")
                .map { it.removeSurrounding("\"") }
                .toList()
        )
}

internal fun String.injectUnions(): String =
    UNIONS.fold(this) { acc, union ->
        acc.replace(union.source, union.name)
    }

internal val UNIONS = listOf(
    Union(
        name = "SourceFileType",
        source = """"js" | "dts"""",
    ),
    Union(
        name = "IncludeInlayParameterNameHints",
        source = """"none" | "literals" | "all"""",
    ),
    Union(
        name = "MatchKind",
        source = """"exact" | "prefix" | "substring" | "camelCase"""",
    ),
    Union(
        name = "PerformanceEventKind",
        source = """"UpdateGraph" | "CreatePackageJsonAutoImportProvider"""",
    ),
    Union(
        name = "QuotePreference",
        source = """"auto" | "double" | "single"""",
    ),
    Union(
        name = "ImportModuleSpecifierPreference",
        source = """"shortest" | "project-relative" | "relative" | "non-relative"""",
    ),
    Union(
        name = "ImportModuleSpecifierEnding",
        source = """"auto" | "minimal" | "index" | "js"""",
    ),
    Union(
        name = "IncludePackageJsonAutoImports",
        source = """"auto" | "on" | "off"""",
    ),
    Union(
        name = "JsxAttributeCompletionStyle",
        source = """"auto" | "braces" | "none"""",
    ),
)
