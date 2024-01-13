// Automatically generated - do not modify!

@file:Suppress(
    "NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package typescript

import seskar.js.JsIntValue
import seskar.js.JsVirtual

@JsVirtual
sealed external interface ModuleResolutionKind {
    companion object {
        @JsIntValue(1)
        val Classic: ModuleResolutionKind

        /**
         * @deprecated
         * `NodeJs` was renamed to `Node10` to better reflect the version of Node that it targets.
         * Use the new name or consider switching to a modern module resolution target.
         */
        @JsIntValue(2)
        val NodeJs: ModuleResolutionKind

        @JsIntValue(2)
        val Node10: ModuleResolutionKind

        @JsIntValue(3)
        val Node16: ModuleResolutionKind

        @JsIntValue(99)
        val NodeNext: ModuleResolutionKind

        @JsIntValue(100)
        val Bundler: ModuleResolutionKind
    }
}
