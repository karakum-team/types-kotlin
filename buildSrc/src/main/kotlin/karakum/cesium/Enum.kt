package karakum.cesium

internal class Enum(
    override val source: Definition,
) : Declaration(), IEnum {
    override val name: String =
        source.defaultName

    override fun toCode(): String {
        var body = source.body
            .substringAfter("\n    ")
            .removeSuffix("}")
            .replace(",\n     *", "__COMMA__\n     *")
            .split(Regex(""",\n\s+"""))
            .asSequence()
            .map { it.replace("__COMMA__\n", ",\n") }
            .flatMap { parseTopDefinition(it) }
            .map { EnumConstant(it, this) }
            .joinToString("\n\n") {
                it.toCode()
            }

        if (!LAZY_MODE)
            body += "\n\n;\n"

        val type = if (LAZY_MODE) {
            "object /* enum */"
        } else {
            "enum class"
        }
        return DEFAULT_PACKAGE +
                source.doc(DocLink(this)) +
                "\n\n" +
                "external $type $name {\n\n$body\n}"
    }

    companion object {
        const val PREFIX = "export const enum "
    }
}

// TODO: describe value in comments
internal class EnumConstant(
    override val source: Definition,
    private val parent: Enum,
) : Declaration() {
    override val name: String =
        source.body.split(" = ")[0]

    override fun toCode(): String {
        val doc = source.doc()
        val body = if (LAZY_MODE) {
            "val $name: ${parent.name}"
        } else {
            "$name,"
        }

        return if (doc.isNotBlank()) {
            "$doc\n$body"
        } else {
            body
        }
    }
}
