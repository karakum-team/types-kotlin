// Automatically generated - do not modify!

package web.intl

sealed external interface DateTimeFormat {
    fun formatRange(
        startDate: Any, /* Date | number | bigint */
        endDate: Any, /* Date | number | bigint */
    ): String

    fun formatRangeToParts(
        startDate: Any, /* Date | number | bigint */
        endDate: Any, /* Date | number | bigint */
    ): DateTimeRangeFormatPart[]
}
