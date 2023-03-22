package karakum.cesium

import karakum.common.Suppress
import karakum.common.Suppress.NESTED_CLASS_IN_EXTERNAL_INTERFACE

internal class Interface(
    source: Definition,
) : TypeBase(source) {
    override val typeName: String = "interface"
    override var companion: HasMembers? = null

    override fun suppresses(): List<Suppress> {
        var result = super.suppresses()
        if (companion != null && name != PACKABLE)
            result = result + NESTED_CLASS_IN_EXTERNAL_INTERFACE

        return result
    }

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
