// Automatically generated - do not modify!

@file:JsQualifier("Intl")

package web.intl

sealed external class Segmenter {
    /**
     * Returns `Segments` object containing the segments of the input string, using the segmenter's locale and granularity.
     *
     * @param input - The text to be segmented as a `string`.
     *
     * @returns A new iterable Segments object containing the segments of the input string, using the segmenter's locale and granularity.
     */
    fun segment(input: String): Segments
    fun resolvedOptions(): ResolvedSegmenterOptions
}
