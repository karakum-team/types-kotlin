interface ExecOutput {
    /**The exit code of the process */
    exitCode: number;
    /**The entire stdout of the process as a string */
    stdout: string;
    /**The entire stderr of the process as a string */
    stderr: string;
}