// Automatically generated - do not modify!

package node.vm

import node.Module

sealed external interface RunningCodeInNewContextOptions : RunningScriptInNewContextOptions {
    var cachedData: Any? /* ScriptOptions['cachedData'] */
    var importModuleDynamically: ((specifier: String, script: Script, importAssertions: Any) -> Module /* ScriptOptions['importModuleDynamically'] */)?
}
