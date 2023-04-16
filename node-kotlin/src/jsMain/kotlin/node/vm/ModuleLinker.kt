// Automatically generated - do not modify!

package node.vm

import js.promise.PromiseResult
import node.Module

typealias ModuleLinker = (
    specifier: String,
    referencingModule: Module,
    extra: Any, /* { assert: Object }  */
) -> PromiseResult<Module>
