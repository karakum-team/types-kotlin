package karakum.cesium

internal const val JS_FUNCTION = "Function"

private const val HTML_ELEMENT = "org.w3c.dom.HTMLElement"
private const val PROMISE = "kotlin.js.Promise"

private val CLASS_REGEX = Regex("""[\w\d]+""")
private const val CALL_DELIMITER = "."

private val WHITE_LIST = setOf(
    "binarySearchComparator",
    "createElevationBandMaterialBand",
    "createElevationBandMaterialEntry",
    "exportKmlModelCallback",
    "mergeSortComparator",
    "requestAnimationFrameCallback",

    "GeoJsonDataSource.describe"
)

private val STANDARD_TYPE_MAP = mapOf(
    "any" to "Any",
    "object" to "Any",

    "boolean" to "Boolean",
    "number" to "Double",
    "string" to "String",

    "void" to "Unit",
    "undefined" to "Nothing?",

    JS_FUNCTION to "Function<*>",
    "Date" to "kotlin.js.Date",

    "Element" to "org.w3c.dom.Element",
    "HTMLElement" to "org.w3c.dom.HTMLElement",
    "HTMLImageElement" to "org.w3c.dom.HTMLImageElement",
    "HTMLCanvasElement" to "org.w3c.dom.HTMLCanvasElement",
    "HTMLVideoElement" to "org.w3c.dom.HTMLVideoElement",
    "HTMLIFrameElement" to "org.w3c.dom.HTMLIFrameElement",

    "Document" to "org.w3c.dom.Document",
    "XMLDocument" to "org.w3c.dom.XMLDocument",

    "ImageData" to "org.w3c.dom.ImageData",

    "Blob" to "org.w3c.files.Blob",

    "ArrayBuffer" to "kotlinx.js.ArrayBuffer",
    "Uint8Array" to "kotlinx.js.Uint8Array",
    "Uint16Array" to "kotlinx.js.Uint16Array",
    "Float32Array" to "kotlinx.js.Float32Array",
    "Float64Array" to "kotlinx.js.Float64Array",

    "CameraEventType | any[] | undefined" to "CameraEventType?",
    "any[] | GeometryInstance" to "GeometryInstance",
    "GeometryInstance[] | GeometryInstance" to "ReadonlyArray<GeometryInstance>",

    "Promise<HTMLImageElement | HTMLCanvasElement> | undefined" to "$PROMISE<$HTML_ELEMENT>?",
    "Promise<ImageryTypes | CompressedTextureBuffer> | undefined" to "$PROMISE<Any /* ImageryTypes | CompressedTextureBuffer */>?",
    "undefined | Promise<void>" to "$PROMISE<Nothing?>?",
    "Cartesian2 | Cartesian3" to "Cartesian3",

    "Entity | Entity.ConstructorOptions" to "Entity",
    "HeadingPitchRollValues | DirectionUp" to CameraOrientation.name,

    "Resource | string" to "Resource",
    "string | Resource" to "Resource",
    "Credit | string" to "Credit",
    "string | number" to "String",

    "string | string[]" to "ReadonlyArray<String>",
    "number[] | Cartesian3[]" to "ReadonlyArray<Cartesian3 /* or number */>",

    "Event" to DefaultEvent.name,
    PACKABLE to "$PACKABLE<*>",
    "CzmlDataSource.UpdaterFunction" to "UpdaterFunction",

    "ModelAnimation.AnimationTimeCallback" to "AnimationTimeCallback",
    "ModelExperimentalAnimation.AnimationTimeCallback" to "AnimationTimeCallback",
)

internal fun kotlinType(
    type: String,
    name: String? = null,
): String {
    if (type in WHITE_LIST)
        return type

    if (type.startsWith("Event<"))
        return type
            .replace("arg0: this, ", "")
            .replace(" => void", " -> Unit")
            .replace(": boolean", ": Boolean")
            .replace("EntityCluster.", "")
            .replace("EntityCollection.", "")
            .replace("TerrainProvider.ErrorEvent", "* /* ErrorEvent */")

    if (type.startsWith("Record<"))
        return type

    if (type == "number") {
        return if (isInteger(name)) "Int" else "Double"
    }

    if (type == JS_FUNCTION && name == "listener")
        return "() -> Unit"

    if (type == "any" && name == "orientation")
        return CameraOrientation.name

    if (STANDARD_TYPE_MAP.containsKey(type))
        return STANDARD_TYPE_MAP.getValue(type)

    if (type.isClassLike())
        return if (CALL_DELIMITER in type) {
            val (parent, alias) = type.split(CALL_DELIMITER)
            parent + CALL_DELIMITER + applyCallbackFix(alias)
        } else type

    if (type.endsWith(" | undefined") && type.indexOf("|") == type.lastIndexOf("|"))
        return kotlinType(type.removeSuffix(" | undefined"), name) + "?"

    if (type.endsWith("[]") && "|" !in type)
        return "ReadonlyArray<${kotlinType(type.removeSuffix("[]"))}>"

    val promiseResult = type.removeSurrounding("Promise<", ">")
    if (promiseResult != type)
        return "$PROMISE<${kotlinType(promiseResult)}>"

    if (type == "Element | string")
        return kotlinType("Element")

    return "dynamic"
}

private fun String.isClassLike(): Boolean =
    if (CALL_DELIMITER in this) {
        val types = split(CALL_DELIMITER)
        types.size == 2 && types[0].isClassLike() && applyCallbackFix(types[1]).isClassLike()
    } else {
        CLASS_REGEX.matches(this) && get(0) == get(0).toUpperCase()
    }
