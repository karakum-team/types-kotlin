class ToolRunner extends events.EventEmitter {
    constructor(toolPath: string, args?: string[], options?: im.ExecOptions);

    private toolPath;
    private args;
    private options;

    /**
     * Exec a tool.
     * Output will be streamed to the live console.
     * Returns promise with return code
     *
     * @param     tool     path to tool to exec
     * @param     options  optional exec options.  See ExecOptions
     * @returns   number
     */
    exec(): Promise<number>;
}
