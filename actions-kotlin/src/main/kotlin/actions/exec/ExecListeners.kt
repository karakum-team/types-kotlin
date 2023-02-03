package actions.exec

external interface ExecListeners {
    /** A call back for each buffer of stdout */
    // stdout?: (data: Buffer) => void
    /** A call back for each buffer of stderr */
    // stderr?: (data: Buffer) => void
    /** A call back for each line of stdout */
    // stdline?: (data: string) => void
    /** A call back for each line of stderr */
    // errline?: (data: string) => void
    /** A call back for each debug log */
    // debug?: (data: string) => void
}