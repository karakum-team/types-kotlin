package karakum.query

class Interface(
    override val source: String,
    fixAction: Boolean,
) : TypeBase() {
    override val name: String =
        getTypeName(source, JsTypeKeyword.INTERFACE, fixAction)

    override val openByDefault: Boolean = false

    override fun toCode(): String {
        val extends = parentType?.let {
            (if (typeParameters.isNotEmpty()) "\n" else "") + ": ${it.replace("BaseResult<", "Result<")}"
        } ?: ""

        when (name) {
            "QueryMeta",
            "MutationMeta",
            -> {
                return "external interface $name : Record<String, Any>"
            }

            "HydrateOptions" -> {
                check(source == HYDRATE_OPTIONS_SOURCE)
                return HYDRATE_OPTIONS_CODE
            }
        }

        val body = when {
            name.startsWith("MutationObserver")
            -> content.replaceFirst("status: QueryStatus", "status: MutationStatus")

            name.startsWith("NotifyEvent")
            -> content.replaceFirst("type: Type /*", "type: NotifyEventType /*")

            else -> content
        }

        return "external interface $name ${formatParameters(typeParameters)} $extends {\n$body\n}"
    }
}
