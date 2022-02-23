package karakum.cesium

internal class TopType(
    override val source: Definition,
) : Declaration() {
    override val name: String =
        source.defaultName

    override fun toCode(): String =
        DEFAULT_PACKAGE +
                source.doc() +
                "\n" +
                typeDeclaration(source.body, true)

    companion object {
        const val PREFIX = "export type "
    }
}
