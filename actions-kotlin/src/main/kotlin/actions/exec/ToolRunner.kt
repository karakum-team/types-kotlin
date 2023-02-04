// Automatically generated - do not modify!

@file:JsModule("@actions/exec")

package actions.exec

import kotlin.js.Promise

external class ToolRunner : events.EventEmitter {
    // constructor(toolPath: string, args?: string[], options?: im.ExecOptions)
    // private toolPath
    // private args
    // private options
    /**
     * Exec a tool.
     * Output will be streamed to the live console.
     * Returns promise with return code
     *
     * @param     tool     path to tool to exec
     * @param     options  optional exec options.  See ExecOptions
     * @returns   number
     */
    fun exec(): Promise<Number>
}
