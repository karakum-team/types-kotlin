package karakum.cesium

internal class Namespace(
    source: Definition,
) : TypeBase(source) {
    var parentName: String? = null

    override val docName: String
        get() = if (parentName != null) {
            "$parentName.${super.docName}"
        } else {
            super.docName
        }

    override val typeName: String = "object"
    override val companion: HasMembers? = null
    override val staticBody: Boolean = true

    companion object {
        const val PREFIX = "export namespace "
    }
}
