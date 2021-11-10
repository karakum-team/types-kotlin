/**
 * A PathPattern is used to match on some portion of a URL pathname.
 */
export interface PathPattern {
    //--delimiter--//
    /**
     * A string to match against a URL pathname. May contain `:id`-style segments
     * to indicate placeholders for dynamic parameters. May also end with `/*` to
     * indicate matching the rest of the URL pathname.
     */
    path: string;
    //--delimiter--//
    /**
     * Should be `true` if the static portions of the `path` should be matched in
     * the same case.
     */
    caseSensitive?: boolean;
    //--delimiter--//
    /**
     * Should be `true` if this pattern should match the entire URL pathname.
     */
    end?: boolean;
}
