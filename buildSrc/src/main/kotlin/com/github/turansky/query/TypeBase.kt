package com.github.turansky.query

abstract class TypeBase : Declaration() {
    abstract val openByDefault: Boolean

    protected val typeParameters: List<String> by lazy {
        var body = source
            .substringBefore(" {")

        if ("$name extends " in body) {
            return@lazy emptyList<String>()
        }

        if ("> extends " in body) {
            body = body.substringBefore("> extends ") + ">"
        }

        parseTypeParameters(body)
    }

    protected val parentType: String? by lazy {
        if (name.endsWith("Props"))
            return@lazy when (name) {
                "QueryClientProviderProps",
                -> "react.PropsWithChildren"

                "QueryErrorResetBoundaryProps",
                "HydrateProps",
                -> "react.Props"

                else -> TODO()
            }

        val line = source.substringBefore(" {")
        if (" extends " !in line)
            return@lazy null

        var type = if ("$name<" in line) {
            line.substringAfterLast("> extends ", "")
                .takeIf { it.isNotEmpty() }
                ?: return@lazy null
        } else {
            line.substringAfterLast(" extends ")
        }

        if (type == "Subscribable")
            type = "Subscribable<Listener>"

        if (type.startsWith("QueryObserver<") && type.count { it == ',' } == 3) {
            type = type.removeSuffix(">") + ", QueryKey>"
        }

        type
    }

    val parentName: String? by lazy {
        parentType?.substringBefore("<")
    }

    val members: List<Member> by lazy {
        val body = source.substringAfter("\n")
            .substringBeforeLast("\n")

        if (body != "}") {
            val optionsMode = name.endsWith("Result") || name == "MutationState"
            body.splitToSequence("\n")
                .map { it.removePrefix("    ") }
                .map { it.removeSuffix(";") }
                .mapNotNull { member(it, openByDefault, optionsMode) }
                .toList()
        } else emptyList()
    }

    val content: String by lazy {
        members.asSequence()
            .filter { it !is Constructor }
            .map { it.toCode() }
            .joinToString("\n")
    }
}

private fun member(
    source: String,
    open: Boolean,
    optionsMode: Boolean,
): Member? {
    if (source.startsWith("private ") || source.startsWith(" ") || source.startsWith("/"))
        return null

    if (source.startsWith("constructor("))
        return Constructor(source)

    val i1 = source.indexOf(":")
    val i2 = source.indexOf("(")
    return if (i1 != -1 && (i2 == -1 || (i1 < i2))) {
        Property(source, !optionsMode)
    } else {
        Method(source)
    }.also { it.open = open }
}
