package karakum.common

internal fun suspendFunctions(
    name: String,
    parameters: List<Parameter>,
    returnType: String,
): ConversionResult? {
    val promiseResult = returnType.removeSurrounding("Promise<", ">")
    if (promiseResult == returnType)
        return null

    val endIndex = parameters.lastIndex + 1
    val startIndex = parameters.indexOfFirst { it.optional }
        .takeIf { it != -1 }
        ?: endIndex

    val body = (startIndex..endIndex)
        .map { parameters.subList(0, it) }
        .map { it.map { it.copy(optional = false) } }
        .map { params -> suspendFunction(name, params, promiseResult) }
        .joinToString("\n\n")

    return ConversionResult(name, body)
}

private fun suspendFunction(
    name: String,
    parameters: List<Parameter>,
    returnType: String,
): String {
    val params = parameters
        .takeIf { it.isNotEmpty() }
        ?.joinToString(",\n", "\n", ",\n")
        ?: ""

    val declaration = "suspend fun $name($params)"

    val callParams = parameters
        .takeIf { it.isNotEmpty() }
        ?.joinToString(",\n", "\n", ",\n") {
            "${it.name} = ${it.name}"
        }
        ?: ""

    val call = "${name}Async($callParams).await()"

    return if (returnType != "Void") {
        "$declaration : $returnType =\n $call"
    } else {
        "$declaration {\n $call \n}"
    }
}
