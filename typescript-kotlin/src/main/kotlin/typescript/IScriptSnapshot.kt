// Automatically generated - do not modify!

package typescript

/**
 * Represents an immutable snapshot of a script at a specified time.Once acquired, the
 * snapshot is observably immutable. i.e. the same calls with the same parameters will return
 * the same values.
 */
external interface IScriptSnapshot {
    /*
    /** Gets a portion of the script snapshot specified by [start, end). */
    getText(start: number, end: number): string;
    /** Gets the length of this script snapshot. */
    getLength(): number;
    /**
     * Gets the TextChangeRange that describe how the text changed between this text and
     * an older version.  This information is used by the incremental parser to determine
     * what sections of the script need to be re-parsed.  'undefined' can be returned if the
     * change range cannot be determined.  However, in that case, incremental parsing will
     * not happen and the entire document will be re - parsed.
     */
    getChangeRange(oldSnapshot: IScriptSnapshot): TextChangeRange | undefined;
    /** Releases all resources held by this script snapshot */
    dispose?(): void;
}
namespace ScriptSnapshot {
    function fromString(text: string): IScriptSnapshot;
    */
}
