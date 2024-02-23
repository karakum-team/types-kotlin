// Automatically generated - do not modify!

@file:Suppress(
"NESTED_CLASS_IN_EXTERNAL_INTERFACE",
)

package web.fs

import seskar.js.JsValue
import seskar.js.JsVirtual
import web.file.File

        @JsVirtual
        external sealed interface FileSystemHandleKind {
            companion object {
                @JsValue("directory")
val directory : FileSystemHandleKind.directory
@JsValue("file")
val file : FileSystemHandleKind.file
            }
            
            sealed interface directory : FileSystemHandleKind
sealed interface file : FileSystemHandleKind
        }
