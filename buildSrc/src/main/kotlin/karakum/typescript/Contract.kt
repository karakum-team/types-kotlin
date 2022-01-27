package karakum.typescript

internal fun addContractSupport(
    result: ConversionResult,
): Sequence<ConversionResult> {
    val (name, body) = result

    if ("external fun " !in body)
        return sequenceOf(result)

    val type = body.substringAfter("): Boolean /* node is ", "")
        .substringBefore(" */")

    if (type.isEmpty() || " | " in type)
        return sequenceOf(result)

    val originalBody = "import typescript.*\n\n" +
            body.replace("external fun ", "internal external fun ")

    val original = ConversionResult(
        name = name,
        body = originalBody,
        pkg = Package.TYPESCRIPT_RAW,
    )

    val functionBody = """{
        contract {
            returns(true) implies (node is $type)
        }
    
        return typescript.raw.$name(node)
    }"""

    val contractBody = "import kotlin.contracts.contract\n\n" +
            body.replace("external fun ", "fun ")
                .substringBefore(" /* node is ") + functionBody

    val contract = ConversionResult(
        name = name,
        body = contractBody,
    )

    return sequenceOf(original, contract)
}
