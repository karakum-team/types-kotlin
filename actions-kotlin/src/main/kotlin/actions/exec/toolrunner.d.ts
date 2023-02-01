class ToolRunner extends events.EventEmitter {
    constructor(toolPath: string, args?: string[], options?: im.ExecOptions);

    private toolPath;
    private args;
    private options;
    private _debug;
    private _getCommandString;
    private _processLineBuffer;
    private _getSpawnFileName;
    private _getSpawnArgs;
    private _endsWith;
    private _isCmdFile;
    private _windowsQuoteCmdArg;
    private _uvQuoteCmdArg;
    private _cloneExecOptions;
    private _getSpawnOptions;

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
