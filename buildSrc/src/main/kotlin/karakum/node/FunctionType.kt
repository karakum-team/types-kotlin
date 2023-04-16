package karakum.node

internal fun functionType(type: String): String? {
    if (type.startsWith("(") && "=>" in type) {
        val params = type.substringBeforeLast("=>").trim()
        val returnType = type.substringAfterLast("=>").trim()

        val kotlinParams = params
            .substringAfter("(")
            .replaceFunctionType()
            .substringBeforeLast(")")
            .split(", ", ",")
            .filter { it.isNotEmpty() }
            .map {
                var paramName = it.substringBeforeLast(":").trim()
                var paramType = it.substringAfterLast(":").trim()

                if (paramName.endsWith("?")) {
                    paramName = paramName.removeSuffix("?")
                    paramType = "$paramType | undefined"
                }

                "$paramName: ${kotlinType(paramType, paramName)}"
            }
            .joinToString(", ")

        val kotlinReturnType = kotlinType(returnType, "")

        return "($kotlinParams) -> $kotlinReturnType"
    }

    return null
}

internal fun String.replaceFunctionType() =
    replace("""(\(.+?\) => .+)([,\n)])""".toRegex()) {
        val functionType = it.groups[1]?.value
        val suffix = it.groups[2]?.value ?: ""

        if (functionType != null) {
            "${kotlinType(functionType, "")}${suffix}"
        } else {
            it.value
        }
    }
