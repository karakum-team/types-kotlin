// Automatically generated - do not modify!

package typescript

external interface Program : ScriptReferenceHost {
    /*
    getCurrentDirectory(): string;
    /**
     * Get a list of root file names that were passed to a 'createProgram'
     */
    getRootFileNames(): readonly string[];
    /**
     * Get a list of files in the program
     */
    getSourceFiles(): readonly SourceFile[];
    /**
     * Emits the JavaScript and declaration files.  If targetSourceFile is not specified, then
     * the JavaScript and declaration files will be produced for all the files in this program.
     * If targetSourceFile is specified, then only the JavaScript and declaration for that
     * specific file will be generated.
     *
     * If writeFile is not specified then the writeFile callback from the compiler host will be
     * used for writing the JavaScript and declaration files.  Otherwise, the writeFile parameter
     * will be invoked when writing the JavaScript and declaration files.
     */
    emit(targetSourceFile?: SourceFile, writeFile?: WriteFileCallback, cancellationToken?: CancellationToken, emitOnlyDtsFiles?: boolean, customTransformers?: CustomTransformers): EmitResult;
    getOptionsDiagnostics(cancellationToken?: CancellationToken): readonly Diagnostic[];
    getGlobalDiagnostics(cancellationToken?: CancellationToken): readonly Diagnostic[];
    getSyntacticDiagnostics(sourceFile?: SourceFile, cancellationToken?: CancellationToken): readonly DiagnosticWithLocation[];
    /** The first time this is called, it will return global diagnostics (no location). */
    getSemanticDiagnostics(sourceFile?: SourceFile, cancellationToken?: CancellationToken): readonly Diagnostic[];
    getDeclarationDiagnostics(sourceFile?: SourceFile, cancellationToken?: CancellationToken): readonly DiagnosticWithLocation[];
    getConfigFileParsingDiagnostics(): readonly Diagnostic[];
    /**
     * Gets a type checker that can be used to semantically analyze source files in the program.
     */
    getTypeChecker(): TypeChecker;
    getNodeCount(): number;
    getIdentifierCount(): number;
    getSymbolCount(): number;
    getTypeCount(): number;
    getInstantiationCount(): number;
    getRelationCacheSizes(): {
        assignable: number;
        identity: number;
        subtype: number;
        strictSubtype: number;
    };
    isSourceFileFromExternalLibrary(file: SourceFile): boolean;
    isSourceFileDefaultLibrary(file: SourceFile): boolean;
    getProjectReferences(): readonly ProjectReference[] | undefined;
    getResolvedProjectReferences(): readonly (ResolvedProjectReference | undefined)[] | undefined;
    */
}
