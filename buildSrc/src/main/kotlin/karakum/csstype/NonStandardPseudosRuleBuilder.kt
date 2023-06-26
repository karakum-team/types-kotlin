package karakum.csstype

import karakum.common.ConversionResult

const val NON_STANDARD_PSEUDOS_RULE_BUILDER = "NonStandardPseudosRuleBuilder"

private val NON_STANDARD_PSEUDO_SIMPLE_ELEMENTS = listOf(
    "::-moz-color-swatchNon-standard",
    "::-moz-focus-innerNon-standard",
    "::-moz-list-bulletNon-standard",
    "::-moz-list-numberNon-standard",
    "::-moz-pageNon-standard",
    "::-moz-page-sequenceNon-standard",
    "::-moz-progress-barNon-standard",
    "::-moz-range-progressNon-standard",
    "::-moz-range-thumbNon-standard",
    "::-moz-range-trackNon-standard",
    "::-moz-scrolled-page-sequenceNon-standard",
    "::-webkit-inner-spin-buttonNon-standard",
    "::-webkit-meter-barNon-standard",
    "::-webkit-meter-even-less-good-valueNon-standard",
    "::-webkit-meter-inner-elementNon-standard",
    "::-webkit-meter-optimum-valueNon-standard",
    "::-webkit-meter-suboptimum-valueNon-standard",
    "::-webkit-outer-spin-buttonNon-standard",
    "::-webkit-progress-barNon-standard",
    "::-webkit-progress-inner-elementNon-standard",
    "::-webkit-progress-valueNon-standard",
    "::-webkit-scrollbarNon-standard",
    "::-webkit-search-cancel-buttonNon-standard",
    "::-webkit-search-results-buttonNon-standard",
    "::-webkit-slider-runnable-trackNon-standard",
    "::-webkit-slider-thumbNon-standard",
)

// language=Kotlin
private val BODY = """
import csstype.$RULE_BUILDER

interface $NON_STANDARD_PSEUDOS_RULE_BUILDER<T : Any> : $RULE_BUILDER<T> {
    ${
    NON_STANDARD_PSEUDO_SIMPLE_ELEMENTS.joinToString(
        separator = "\n\n",
        transform = ::convertSimplePseudo,
    )
}
}
""".trimIndent()

internal fun NonStandardPseudosRuleBuilder(): ConversionResult =
    ConversionResult(
        name = NON_STANDARD_PSEUDOS_RULE_BUILDER,
        body = BODY,
    )
