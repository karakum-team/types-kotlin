// Automatically generated - do not modify!

package typescript

/** Host that has watch functionality used in --watch mode */
external interface WatchCompilerHost<T : BuilderProgram> : ProgramHost<T>, WatchHost {
    /*
    /** Instead of using output d.ts file from project reference, use its source file */
    useSourceOfProjectReferenceRedirect?(): boolean;
    /** If provided, use this method to get parsed command lines for referenced projects */
    getParsedCommandLine?(fileName: string): ParsedCommandLine | undefined;
    /** If provided, callback to invoke after every new program creation */
    afterProgramCreate?(program: T): void;
    */
}
