package karakum.browser

private val CLASSES = listOf(
    "SVGElement" to true,

    "SVGAElement" to true,
    "SVGAnimateElement" to false,
    "SVGAnimateMotionElement" to false,
    "SVGAnimateTransformElement" to false,
    "SVGCircleElement" to true,
    "SVGClipPathElement" to true,
    "SVGDefsElement" to true,
    "SVGDescElement" to true,
    "SVGEllipseElement" to true,
    "SVGFEBlendElement" to false,
    "SVGFEColorMatrixElement" to false,
    "SVGFEComponentTransferElement" to false,
    "SVGFECompositeElement" to false,
    "SVGFEConvolveMatrixElement" to false,
    "SVGFEDiffuseLightingElement" to false,
    "SVGFEDisplacementMapElement" to false,
    "SVGFEDistantLightElement" to false,
    "SVGFEDropShadowElement" to false,
    "SVGFEFloodElement" to false,
    "SVGFEFuncAElement" to false,
    "SVGFEFuncBElement" to false,
    "SVGFEFuncGElement" to false,
    "SVGFEFuncRElement" to false,
    "SVGFEGaussianBlurElement" to false,
    "SVGFEImageElement" to false,
    "SVGFEMergeElement" to false,
    "SVGFEMergeNodeElement" to false,
    "SVGFEMorphologyElement" to false,
    "SVGFEOffsetElement" to false,
    "SVGFEPointLightElement" to false,
    "SVGFESpecularLightingElement" to false,
    "SVGFESpotLightElement" to false,
    "SVGFETileElement" to false,
    "SVGFETurbulenceElement" to false,
    "SVGFilterElement" to false,
    "SVGForeignObjectElement" to true,
    "SVGGElement" to true,
    "SVGImageElement" to true,
    "SVGLineElement" to true,
    "SVGLinearGradientElement" to true,
    "SVGMarkerElement" to true,
    "SVGMaskElement" to true,
    "SVGMetadataElement" to true,
    "SVGMPathElement" to false,
    "SVGPathElement" to true,
    "SVGPatternElement" to true,
    "SVGPolygonElement" to true,
    "SVGPolylineElement" to true,
    "SVGRadialGradientElement" to true,
    "SVGRectElement" to true,
    "SVGScriptElement" to true,
    "SVGSetElement" to false,
    "SVGStopElement" to true,
    "SVGStyleElement" to true,
    "SVGSVGElement" to true,
    "SVGSwitchElement" to true,
    "SVGSymbolElement" to true,
    "SVGTextElement" to true,
    "SVGTextPathElement" to true,
    "SVGTitleElement" to true,
    "SVGTSpanElement" to true,
    "SVGUseElement" to true,
    "SVGViewElement" to true,
)

internal fun svgAliases(): List<ConversionResult> =
CLASSES.map { (name, exists) ->
    val body = if (exists) {
        val aliasPkg = when(name) {
            "SVGClipPathElement",
            "SVGMaskElement",
                -> "org.w3c.css.masking"

            else -> "org.w3c.dom.svg"
        }

        "typealias $name = $aliasPkg.$name"
    } else {
        "sealed external class $name : SVGElement"
    }

    ConversionResult(
        name = name,
        body = body,
        pkg = "dom.svg",
    )
}
