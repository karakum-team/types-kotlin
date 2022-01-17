// Automatically generated - do not modify!

package typescript

external interface LanguageService {
    /*
    /** This is used as a part of restarting the language service. */
    cleanupSemanticCache(): void;
    /**
     * Gets errors indicating invalid syntax in a file.
     *
     * In English, "this cdeo have, erorrs" is syntactically invalid because it has typos,
     * grammatical errors, and misplaced punctuation. Likewise, examples of syntax
     * errors in TypeScript are missing parentheses in an `if` statement, mismatched
     * curly braces, and using a reserved keyword as a variable name.
     *
     * These diagnostics are inexpensive to compute and don't require knowledge of
     * other files. Note that a non-empty result increases the likelihood of false positives
     * from `getSemanticDiagnostics`.
     *
     * While these represent the majority of syntax-related diagnostics, there are some
     * that require the type system, which will be present in `getSemanticDiagnostics`.
     *
     * @param fileName A path to the file you want syntactic diagnostics for
     */
    getSyntacticDiagnostics(fileName: string): DiagnosticWithLocation[];
    /**
     * Gets warnings or errors indicating type system issues in a given file.
     * Requesting semantic diagnostics may start up the type system and
     * run deferred work, so the first call may take longer than subsequent calls.
     *
     * Unlike the other get*Diagnostics functions, these diagnostics can potentially not
     * include a reference to a source file. Specifically, the first time this is called,
     * it will return global diagnostics with no associated location.
     *
     * To contrast the differences between semantic and syntactic diagnostics, consider the
     * sentence: "The sun is green." is syntactically correct; those are real English words with
     * correct sentence structure. However, it is semantically invalid, because it is not true.
     *
     * @param fileName A path to the file you want semantic diagnostics for
     */
    getSemanticDiagnostics(fileName: string): Diagnostic[];
    /**
     * Gets suggestion diagnostics for a specific file. These diagnostics tend to
     * proactively suggest refactors, as opposed to diagnostics that indicate
     * potentially incorrect runtime behavior.
     *
     * @param fileName A path to the file you want semantic diagnostics for
     */
    getSuggestionDiagnostics(fileName: string): DiagnosticWithLocation[];
    /**
     * Gets global diagnostics related to the program configuration and compiler options.
     */
    getCompilerOptionsDiagnostics(): Diagnostic[];
    /** @deprecated Use getEncodedSyntacticClassifications instead. */
    getSyntacticClassifications(fileName: string, span: TextSpan): ClassifiedSpan[];
    getSyntacticClassifications(fileName: string, span: TextSpan, format: SemanticClassificationFormat): ClassifiedSpan[] | ClassifiedSpan2020[];
    /** @deprecated Use getEncodedSemanticClassifications instead. */
    getSemanticClassifications(fileName: string, span: TextSpan): ClassifiedSpan[];
    getSemanticClassifications(fileName: string, span: TextSpan, format: SemanticClassificationFormat): ClassifiedSpan[] | ClassifiedSpan2020[];
    /** Encoded as triples of [start, length, ClassificationType]. */
    getEncodedSyntacticClassifications(fileName: string, span: TextSpan): Classifications;
    /**
     * Gets semantic highlights information for a particular file. Has two formats, an older
     * version used by VS and a format used by VS Code.
     *
     * @param fileName The path to the file
     * @param position A text span to return results within
     * @param format Which format to use, defaults to "original"
     * @returns a number array encoded as triples of [start, length, ClassificationType, ...].
     */
    getEncodedSemanticClassifications(fileName: string, span: TextSpan, format?: SemanticClassificationFormat): Classifications;
    /**
     * Gets completion entries at a particular position in a file.
     *
     * @param fileName The path to the file
     * @param position A zero-based index of the character where you want the entries
     * @param options An object describing how the request was triggered and what kinds
     * of code actions can be returned with the completions.
     */
    getCompletionsAtPosition(fileName: string, position: number, options: GetCompletionsAtPositionOptions | undefined): WithMetadata<CompletionInfo> | undefined;
    /**
     * Gets the extended details for a completion entry retrieved from `getCompletionsAtPosition`.
     *
     * @param fileName The path to the file
     * @param position A zero based index of the character where you want the entries
     * @param entryName The `name` from an existing completion which came from `getCompletionsAtPosition`
     * @param formatOptions How should code samples in the completions be formatted, can be undefined for backwards compatibility
     * @param source `source` property from the completion entry
     * @param preferences User settings, can be undefined for backwards compatibility
     * @param data `data` property from the completion entry
     */
    getCompletionEntryDetails(fileName: string, position: number, entryName: string, formatOptions: FormatCodeOptions | FormatCodeSettings | undefined, source: string | undefined, preferences: UserPreferences | undefined, data: CompletionEntryData | undefined): CompletionEntryDetails | undefined;
    getCompletionEntrySymbol(fileName: string, position: number, name: string, source: string | undefined): Symbol | undefined;
    /**
     * Gets semantic information about the identifier at a particular position in a
     * file. Quick info is what you typically see when you hover in an editor.
     *
     * @param fileName The path to the file
     * @param position A zero-based index of the character where you want the quick info
     */
    getQuickInfoAtPosition(fileName: string, position: number): QuickInfo | undefined;
    getNameOrDottedNameSpan(fileName: string, startPos: number, endPos: number): TextSpan | undefined;
    getBreakpointStatementAtPosition(fileName: string, position: number): TextSpan | undefined;
    getSignatureHelpItems(fileName: string, position: number, options: SignatureHelpItemsOptions | undefined): SignatureHelpItems | undefined;
    getRenameInfo(fileName: string, position: number, options?: RenameInfoOptions): RenameInfo;
    findRenameLocations(fileName: string, position: number, findInStrings: boolean, findInComments: boolean, providePrefixAndSuffixTextForRename?: boolean): readonly RenameLocation[] | undefined;
    getSmartSelectionRange(fileName: string, position: number): SelectionRange;
    getDefinitionAtPosition(fileName: string, position: number): readonly DefinitionInfo[] | undefined;
    getDefinitionAndBoundSpan(fileName: string, position: number): DefinitionInfoAndBoundSpan | undefined;
    getTypeDefinitionAtPosition(fileName: string, position: number): readonly DefinitionInfo[] | undefined;
    getImplementationAtPosition(fileName: string, position: number): readonly ImplementationLocation[] | undefined;
    getReferencesAtPosition(fileName: string, position: number): ReferenceEntry[] | undefined;
    findReferences(fileName: string, position: number): ReferencedSymbol[] | undefined;
    getDocumentHighlights(fileName: string, position: number, filesToSearch: string[]): DocumentHighlights[] | undefined;
    getFileReferences(fileName: string): ReferenceEntry[];
    /** @deprecated */
    getOccurrencesAtPosition(fileName: string, position: number): readonly ReferenceEntry[] | undefined;
    getNavigateToItems(searchValue: string, maxResultCount?: number, fileName?: string, excludeDtsFiles?: boolean): NavigateToItem[];
    getNavigationBarItems(fileName: string): NavigationBarItem[];
    getNavigationTree(fileName: string): NavigationTree;
    prepareCallHierarchy(fileName: string, position: number): CallHierarchyItem | CallHierarchyItem[] | undefined;
    provideCallHierarchyIncomingCalls(fileName: string, position: number): CallHierarchyIncomingCall[];
    provideCallHierarchyOutgoingCalls(fileName: string, position: number): CallHierarchyOutgoingCall[];
    provideInlayHints(fileName: string, span: TextSpan, preferences: UserPreferences | undefined): InlayHint[];
    getOutliningSpans(fileName: string): OutliningSpan[];
    getTodoComments(fileName: string, descriptors: TodoCommentDescriptor[]): TodoComment[];
    getBraceMatchingAtPosition(fileName: string, position: number): TextSpan[];
    getIndentationAtPosition(fileName: string, position: number, options: EditorOptions | EditorSettings): number;
    getFormattingEditsForRange(fileName: string, start: number, end: number, options: FormatCodeOptions | FormatCodeSettings): TextChange[];
    getFormattingEditsForDocument(fileName: string, options: FormatCodeOptions | FormatCodeSettings): TextChange[];
    getFormattingEditsAfterKeystroke(fileName: string, position: number, key: string, options: FormatCodeOptions | FormatCodeSettings): TextChange[];
    getDocCommentTemplateAtPosition(fileName: string, position: number, options?: DocCommentTemplateOptions): TextInsertion | undefined;
    isValidBraceCompletionAtPosition(fileName: string, position: number, openingBrace: number): boolean;
    /**
     * This will return a defined result if the position is after the `>` of the opening tag, or somewhere in the text, of a JSXElement with no closing tag.
     * Editors should call this after `>` is typed.
     */
    getJsxClosingTagAtPosition(fileName: string, position: number): JsxClosingTagInfo | undefined;
    getSpanOfEnclosingComment(fileName: string, position: number, onlyMultiLine: boolean): TextSpan | undefined;
    toLineColumnOffset?(fileName: string, position: number): LineAndCharacter;
    getCodeFixesAtPosition(fileName: string, start: number, end: number, errorCodes: readonly number[], formatOptions: FormatCodeSettings, preferences: UserPreferences): readonly CodeFixAction[];
    getCombinedCodeFix(scope: CombinedCodeFixScope, fixId: {}, formatOptions: FormatCodeSettings, preferences: UserPreferences): CombinedCodeActions;
    applyCodeActionCommand(action: CodeActionCommand, formatSettings?: FormatCodeSettings): Promise<ApplyCodeActionCommandResult>;
    applyCodeActionCommand(action: CodeActionCommand[], formatSettings?: FormatCodeSettings): Promise<ApplyCodeActionCommandResult[]>;
    applyCodeActionCommand(action: CodeActionCommand | CodeActionCommand[], formatSettings?: FormatCodeSettings): Promise<ApplyCodeActionCommandResult | ApplyCodeActionCommandResult[]>;
    /** @deprecated `fileName` will be ignored */
    applyCodeActionCommand(fileName: string, action: CodeActionCommand): Promise<ApplyCodeActionCommandResult>;
    /** @deprecated `fileName` will be ignored */
    applyCodeActionCommand(fileName: string, action: CodeActionCommand[]): Promise<ApplyCodeActionCommandResult[]>;
    /** @deprecated `fileName` will be ignored */
    applyCodeActionCommand(fileName: string, action: CodeActionCommand | CodeActionCommand[]): Promise<ApplyCodeActionCommandResult | ApplyCodeActionCommandResult[]>;
    getApplicableRefactors(fileName: string, positionOrRange: number | TextRange, preferences: UserPreferences | undefined, triggerReason?: RefactorTriggerReason, kind?: string): ApplicableRefactorInfo[];
    getEditsForRefactor(fileName: string, formatOptions: FormatCodeSettings, positionOrRange: number | TextRange, refactorName: string, actionName: string, preferences: UserPreferences | undefined): RefactorEditInfo | undefined;
    organizeImports(args: OrganizeImportsArgs, formatOptions: FormatCodeSettings, preferences: UserPreferences | undefined): readonly FileTextChanges[];
    getEditsForFileRename(oldFilePath: string, newFilePath: string, formatOptions: FormatCodeSettings, preferences: UserPreferences | undefined): readonly FileTextChanges[];
    getEmitOutput(fileName: string, emitOnlyDtsFiles?: boolean, forceDtsEmit?: boolean): EmitOutput;
    getProgram(): Program | undefined;
    toggleLineComment(fileName: string, textRange: TextRange): TextChange[];
    toggleMultilineComment(fileName: string, textRange: TextRange): TextChange[];
    commentSelection(fileName: string, textRange: TextRange): TextChange[];
    uncommentSelection(fileName: string, textRange: TextRange): TextChange[];
    dispose(): void;
    */
}
