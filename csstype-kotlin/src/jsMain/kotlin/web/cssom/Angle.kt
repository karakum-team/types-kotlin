// Automatically generated - do not modify!

package web.cssom

import seskar.js.JsIntValue
import seskar.js.JsVirtual
import seskar.js.JsValue

sealed external interface Angle

inline val Number.deg: Angle
    get() = "${this}deg".unsafeCast<Angle>()

inline val Number.rad: Angle
    get() = "${this}rad".unsafeCast<Angle>()

inline val Number.grad: Angle
    get() = "${this}grad".unsafeCast<Angle>()

inline val Number.turn: Angle
    get() = "${this}turn".unsafeCast<Angle>()
