package karakum.query

private enum class InterfaceType {
    PROPS,
    JSO,

    // Details - https://youtrack.jetbrains.com/issue/KT-70664
    JSO_WITH_WA,

    ;
}

class Interface(
    override val source: String,
    fixAction: Boolean,
) : TypeBase() {
    override val name: String =
        getTypeName(source, JsTypeKeyword.INTERFACE, fixAction)

    override val openByDefault: Boolean = false

    private val type: InterfaceType by lazy {
        val jsoFixRequired = when (name) {
            "DefaultOptions",
            "FetchContext",
            "InfiniteData",
            "MutateOptions",
            "MutationConfig",
            "QueriesObserverOptions",
            "QueryBehavior",
            "QueryConfig",
            "QueryFunctionContext",
            "QueryState",
            "Retryer",
            "RetryerConfig",
                -> false

            else -> !name.endsWith("Action")
                    && !name.endsWith("Action_1")
        }

        when {
            name.endsWith("Props")
                -> InterfaceType.PROPS

            typeParameters.isNotEmpty() && jsoFixRequired
                -> InterfaceType.JSO_WITH_WA

            else -> InterfaceType.JSO
        }
    }

    override val immutable: Boolean
        get() = super.immutable || type == InterfaceType.JSO

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

            "FetchMeta" -> {
                check(source == FETCH_META_SOURCE)
                return FETCH_META_CODE
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

        val annotations = when (type) {
            InterfaceType.PROPS,
                -> ""

            InterfaceType.JSO,
                -> "@JsPlainObject\n"

            InterfaceType.JSO_WITH_WA,
                -> "// @JsPlainObject\n// Details - https://youtrack.jetbrains.com/issue/KT-70664\n"
        }
        return "${annotations}external interface $name ${formatParameters(typeParameters)} $extends {\n$body\n}"
    }
}
