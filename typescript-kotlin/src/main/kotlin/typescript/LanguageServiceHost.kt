// Automatically generated - do not modify!

package typescript

/**
 * Represents an immutable snapshot of a script at a specified time.Once acquired, the
 * snapshot is observably immutable. i.e. the same calls with the same parameters will return
 * the same values.
 */
external interface LanguageServiceHost : GetEffectiveTypeRootsHost {
    /*
    getCompilationSettings(): CompilerOptions;
    getNewLine?(): string;
    getProjectVersion?(): string;
    getScriptFileNames(): string[];
    getScriptKind?(fileName: string): ScriptKind;
    getScriptVersion(fileName: string): string;
    getScriptSnapshot(fileName: string): IScriptSnapshot | undefined;
    getProjectReferences?(): readonly ProjectReference[] | undefined;
    getLocalizedDiagnosticMessages?(): any;
    getCancellationToken?(): HostCancellationToken;
    getCurrentDirectory(): string;
    getDefaultLibFileName(options: CompilerOptions): string;
    log?(s: string): void;
    trace?(s: string): void;
    error?(s: string): void;
    useCaseSensitiveFileNames?(): boolean;
    readDirectory?(path: string, extensions?: readonly string[], exclude?: readonly string[], include?: readonly string[], depth?: number): string[];
    readFile?(path: string, encoding?: string): string | undefined;
    realpath?(path: string): string;
    fileExists?(path: string): boolean;
    getTypeRootsVersion?(): number;
    resolveModuleNames?(moduleNames: string[], containingFile: string, reusedNames: string[] | undefined, redirectedReference: ResolvedProjectReference | undefined, options: CompilerOptions, containingSourceFile?: SourceFile): (ResolvedModule | undefined)[];
    getResolvedModuleWithFailedLookupLocationsFromCache?(modulename: string, containingFile: string, resolutionMode?: ModuleKind.CommonJS | ModuleKind.ESNext): ResolvedModuleWithFailedLookupLocations | undefined;
    resolveTypeReferenceDirectives?(typeDirectiveNames: string[], containingFile: string, redirectedReference: ResolvedProjectReference | undefined, options: CompilerOptions): (ResolvedTypeReferenceDirective | undefined)[];
    getDirectories?(directoryName: string): string[];
    /**
     * Gets a set of custom transformers to use during emit.
     */
    getCustomTransformers?(): CustomTransformers | undefined;
    isKnownTypesPackageName?(name: string): boolean;
    installPackage?(options: InstallPackageOptions): Promise<ApplyCodeActionCommandResult>;
    writeFile?(fileName: string, content: string): void;
    getParsedCommandLine?(fileName: string): ParsedCommandLine | undefined;
    */
}
