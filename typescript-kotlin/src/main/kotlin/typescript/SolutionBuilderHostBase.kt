// Automatically generated - do not modify!

package typescript

external interface SolutionBuilderHostBase<T : BuilderProgram> : ProgramHost<T> {
    /*
    createDirectory?(path: string): void;
    /**
     * Should provide create directory and writeFile if done of invalidatedProjects is not invoked with
     * writeFileCallback
     */
    writeFile?(path: string, data: string, writeByteOrderMark?: boolean): void;
    getCustomTransformers?: (project: string) => CustomTransformers | undefined;
    getModifiedTime(fileName: string): Date | undefined;
    setModifiedTime(fileName: string, date: Date): void;
    deleteFile(fileName: string): void;
    getParsedCommandLine?(fileName: string): ParsedCommandLine | undefined;
    reportDiagnostic: DiagnosticReporter;
    reportSolutionBuilderStatus: DiagnosticReporter;
    afterProgramEmitAndDiagnostics?(program: T): void;
    */
}
