package karakum.csstype

import karakum.common.ConversionResult

const val EXPERIMENTAL_RULE_BUILDER = "ExperimentalRuleBuilder"

private val EXPERIMENTAL_PSEUDO_SIMPLE_ELEMENTS = listOf(
    "::view-transition-group",
    "::view-transition-image-pair",
    "::view-transition-new",
    "::view-transition-old",
)

private val EXPERIMENTAL_PSEUDO_ADVANCED_ELEMENTS = listOf(
    "::highlight()",
)

// language=Kotlin
private val BODY = """
import csstype.$RULE_BUILDER

interface $EXPERIMENTAL_RULE_BUILDER<T : Any> : $RULE_BUILDER<T> {
    ${
    EXPERIMENTAL_PSEUDO_SIMPLE_ELEMENTS.joinToString(
        separator = "\n\n",
        transform = ::convertSimplePseudo,
    )
}

    ${
    EXPERIMENTAL_PSEUDO_ADVANCED_ELEMENTS.joinToString(
        separator = "\n\n",
        transform = ::convertAdvancedPseudo,
    )
}
}
""".trimIndent()

internal fun ExperimentalRuleBuilder(): ConversionResult =
    ConversionResult(
        name = EXPERIMENTAL_RULE_BUILDER,
        body = BODY,
    )
