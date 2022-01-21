// Automatically generated - do not modify!

package typescript

external interface LanguageServiceHost : GetEffectiveTypeRootsHost {
    fun getCompilationSettings(): CompilerOptions
    val getNewLine: (() -> String)?
    val getProjectVersion: (() -> String)?
    fun getScriptFileNames(): ReadonlyArray<String>
    val getScriptKind: ((fileName: String) -> ScriptKind)?
    fun getScriptVersion(fileName: String): String
    fun getScriptSnapshot(fileName: String): IScriptSnapshot?
    val getProjectReferences: (() -> ReadonlyArray<ProjectReference>?)?
    val getLocalizedDiagnosticMessages: (() -> Any)?
    val getCancellationToken: (() -> HostCancellationToken)?
    fun getCurrentDirectory(): String
    fun getDefaultLibFileName(options: CompilerOptions): String
    val log: ((s: String) -> Unit)?
    val trace: ((s: String) -> Unit)?
    val error: ((s: String) -> Unit)?
    val useCaseSensitiveFileNames: (() -> Boolean)?
    val readDirectory: ((
        path: String,
        extensions: ReadonlyArray<String>?,
        exclude: ReadonlyArray<String>?,
        include: ReadonlyArray<String>?,
        depth: Double?,
    ) -> ReadonlyArray<String>)?
    val readFile: ((
        path: String,
        encoding: String?,
    ) -> String?)?
    val realpath: ((path: String) -> String)?
    val fileExists: ((path: String) -> Boolean)?
    val getTypeRootsVersion: (() -> Double)?
    val resolveModuleNames: ((
        moduleNames: ReadonlyArray<String>,
        containingFile: String,
        reusedNames: ReadonlyArray<String>?,
        redirectedReference: ResolvedProjectReference?,
        options: CompilerOptions,
        containingSourceFile: SourceFile?,
    ) -> dynamic /* (ResolvedModule | undefined)[] */)?
    val getResolvedModuleWithFailedLookupLocationsFromCache: ((
        modulename: String,
        containingFile: String,
        resolutionMode: dynamic, /* ModuleKind.CommonJS | ModuleKind.ESNext */
    ) -> ResolvedModuleWithFailedLookupLocations?)?
    val resolveTypeReferenceDirectives: ((
        typeDirectiveNames: ReadonlyArray<String>,
        containingFile: String,
        redirectedReference: ResolvedProjectReference?,
        options: CompilerOptions,
    ) -> dynamic /* (ResolvedTypeReferenceDirective | undefined)[] */)?
    val getDirectories: ((directoryName: String) -> ReadonlyArray<String>)?

    /**
     * Gets a set of custom transformers to use during emit.
     */
    val getCustomTransformers: (() -> CustomTransformers?)?
    val isKnownTypesPackageName: ((name: String) -> Boolean)?
    val installPackage: ((options: InstallPackageOptions) -> kotlin.js.Promise<ApplyCodeActionCommandResult>)?
    val writeFile: ((
        fileName: String,
        content: String,
    ) -> Unit)?
    val getParsedCommandLine: ((fileName: String) -> ParsedCommandLine?)?
}
