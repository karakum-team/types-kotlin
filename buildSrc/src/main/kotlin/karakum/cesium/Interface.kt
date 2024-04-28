package karakum.cesium

internal class Interface(
    source: Definition,
) : TypeBase(source) {
    override val typeName: String = "interface"
    override var companion: HasMembers? = null

    override fun toCode(): String =
        if (name == PACKABLE) {
            applyPackableFixes(super.toCode())
        } else {
            super.toCode()
        }

    companion object {
        const val PREFIX = "export interface "
    }
}
