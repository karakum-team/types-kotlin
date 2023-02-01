interface Globber {
    /**
     * Returns the search path preceding the first glob segment, from each pattern.
     * Duplicates and descendants of other paths are filtered out.
     *
     * Example 1: The patterns `/foo/*` and `/bar/*` returns `/foo` and `/bar`.
     *
     * Example 2: The patterns `/foo/*` and `/foo/bar/*` returns `/foo`.
     */
    getSearchPaths(): string[];

    /**
     * Returns files and directories matching the glob patterns.
     *
     * Order of the results is not guaranteed.
     */
    glob(): Promise<string[]>;

    /**
     * Returns files and directories matching the glob patterns.
     *
     * Order of the results is not guaranteed.
     */
    globGenerator(): AsyncGenerator<string, void>;
}
