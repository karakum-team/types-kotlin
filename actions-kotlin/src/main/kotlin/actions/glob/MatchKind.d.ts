enum MatchKind {
    /** Not matched */
    None = 0,
    /** Matched if the path is a directory */
    Directory = 1,
    /** Matched if the path is a regular file */
    File = 2,
    /** Matched */
    All = 3
}