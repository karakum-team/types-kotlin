class DefaultGlobber implements Globber {
    private readonly options;
    private readonly patterns;
    private readonly searchPaths;

    private constructor();

    getSearchPaths(): string[];

    glob(): Promise<string[]>;

    globGenerator(): AsyncGenerator<string, void>;

    /**
     * Constructs a DefaultGlobber
     */
    static create(patterns: string, options?: GlobOptions): Promise<DefaultGlobber>;

    private static stat;
}
