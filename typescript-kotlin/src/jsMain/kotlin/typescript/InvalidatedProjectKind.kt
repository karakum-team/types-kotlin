// Automatically generated - do not modify!

@file:Suppress(
"NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package typescript

import seskar.js.JsVirtual
import seskar.js.JsIntValue

        @JsVirtual
        external sealed interface InvalidatedProjectKind {
            companion object {
                @JsIntValue(0)
val Build : InvalidatedProjectKind.Build
@JsIntValue(2)
val UpdateOutputFileStamps : InvalidatedProjectKind.UpdateOutputFileStamps
            }
            
            sealed interface Build : InvalidatedProjectKind
sealed interface UpdateOutputFileStamps : InvalidatedProjectKind
        }
