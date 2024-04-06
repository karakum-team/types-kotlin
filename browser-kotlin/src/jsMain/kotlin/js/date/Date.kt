// Automatically generated - do not modify!

package js.date

external class Date() {
    constructor(value: number)
    constructor(value: string)
    constructor(value: Date)

    /**
     * Creates a new Date.
     * @param year The full year designation is required for cross-century date accuracy. If year is between 0 and 99 is used, then year is assumed to be 1900 + year.
     * @param monthIndex The month as a number between 0 and 11 (January to December).
     * @param date The date as a number between 1 and 31.
     * @param hours Must be supplied if minutes is supplied. A number from 0 to 23 (midnight to 11pm) that specifies the hour.
     * @param minutes Must be supplied if seconds is supplied. A number from 0 to 59 that specifies the minutes.
     * @param seconds Must be supplied if milliseconds is supplied. A number from 0 to 59 that specifies the seconds.
     * @param ms A number from 0 to 999 that specifies the milliseconds.
     */
    constructor(
        year: number,
        monthIndex: number,
        date?:
        number,
        hours?:
        number,
        minutes?:
        number,
        seconds?:
        number,
        ms?:
        number
    )

    /** Returns a string representation of a date. The format of the string depends on the locale. */
    fun toString(): string

    /** Returns a date as a string value. */
    fun toDateString(): string

    /** Returns a time as a string value. */
    fun toTimeString(): string

    /** Returns a value as a string value appropriate to the host environment's current locale. */
    fun toLocaleString(): string

    /** Returns a date as a string value appropriate to the host environment's current locale. */
    fun toLocaleDateString(): string

    /** Returns a time as a string value appropriate to the host environment's current locale. */
    fun toLocaleTimeString(): string

    /** Returns the stored time value in milliseconds since midnight, January 1, 1970 UTC. */
    fun valueOf(): number

    /** Returns the stored time value in milliseconds since midnight, January 1, 1970 UTC. */
    fun getTime(): number

    /** Gets the year, using local time. */
    fun getFullYear(): number

    /** Gets the year using Universal Coordinated Time (UTC). */
    fun getUTCFullYear(): number

    /** Gets the month, using local time. */
    fun getMonth(): number

    /** Gets the month of a Date object using Universal Coordinated Time (UTC). */
    fun getUTCMonth(): number

    /** Gets the day-of-the-month, using local time. */
    fun getDate(): number

    /** Gets the day-of-the-month, using Universal Coordinated Time (UTC). */
    fun getUTCDate(): number

    /** Gets the day of the week, using local time. */
    fun getDay(): number

    /** Gets the day of the week using Universal Coordinated Time (UTC). */
    fun getUTCDay(): number

    /** Gets the hours in a date, using local time. */
    fun getHours(): number

    /** Gets the hours value in a Date object using Universal Coordinated Time (UTC). */
    fun getUTCHours(): number

    /** Gets the minutes of a Date object, using local time. */
    fun getMinutes(): number

    /** Gets the minutes of a Date object using Universal Coordinated Time (UTC). */
    fun getUTCMinutes(): number

    /** Gets the seconds of a Date object, using local time. */
    fun getSeconds(): number

    /** Gets the seconds of a Date object using Universal Coordinated Time (UTC). */
    fun getUTCSeconds(): number

    /** Gets the milliseconds of a Date, using local time. */
    fun getMilliseconds(): number

    /** Gets the milliseconds of a Date object using Universal Coordinated Time (UTC). */
    fun getUTCMilliseconds(): number

    /** Gets the difference in minutes between the time on the local computer and Universal Coordinated Time (UTC). */
    fun getTimezoneOffset(): number

    /**
     * Sets the date and time value in the Date object.
     * @param time A numeric value representing the number of elapsed milliseconds since midnight, January 1, 1970 GMT.
     */
    fun setTime(time: number): number

    /**
     * Sets the milliseconds value in the Date object using local time.
     * @param ms A numeric value equal to the millisecond value.
     */
    fun setMilliseconds(ms: number): number

    /**
     * Sets the milliseconds value in the Date object using Universal Coordinated Time (UTC).
     * @param ms A numeric value equal to the millisecond value.
     */
    fun setUTCMilliseconds(ms: number): number

    /**
     * Sets the seconds value in the Date object using local time.
     * @param sec A numeric value equal to the seconds value.
     * @param ms A numeric value equal to the milliseconds value.
     */
    fun setSeconds(sec: number, ms?: number): number
    /**
     * Sets the seconds value in the Date object using Universal Coordinated Time (UTC).
     * @param sec A numeric value equal to the seconds value.
     * @param ms A numeric value equal to the milliseconds value.
     */
    fun setUTCSeconds(sec: number, ms?: number): number
    /**
     * Sets the minutes value in the Date object using local time.
     * @param min A numeric value equal to the minutes value.
     * @param sec A numeric value equal to the seconds value.
     * @param ms A numeric value equal to the milliseconds value.
     */
    fun setMinutes(min: number, sec?: number, ms?: number): number
    /**
     * Sets the minutes value in the Date object using Universal Coordinated Time (UTC).
     * @param min A numeric value equal to the minutes value.
     * @param sec A numeric value equal to the seconds value.
     * @param ms A numeric value equal to the milliseconds value.
     */
    fun setUTCMinutes(min: number, sec?: number, ms?: number): number
    /**
     * Sets the hour value in the Date object using local time.
     * @param hours A numeric value equal to the hours value.
     * @param min A numeric value equal to the minutes value.
     * @param sec A numeric value equal to the seconds value.
     * @param ms A numeric value equal to the milliseconds value.
     */
    fun setHours(hours: number, min?: number, sec?: number, ms?: number): number
    /**
     * Sets the hours value in the Date object using Universal Coordinated Time (UTC).
     * @param hours A numeric value equal to the hours value.
     * @param min A numeric value equal to the minutes value.
     * @param sec A numeric value equal to the seconds value.
     * @param ms A numeric value equal to the milliseconds value.
     */
    fun setUTCHours(hours: number, min?: number, sec?: number, ms?: number): number
    /**
     * Sets the numeric day-of-the-month value of the Date object using local time.
     * @param date A numeric value equal to the day of the month.
     */
    fun setDate(date: number): number

    /**
     * Sets the numeric day of the month in the Date object using Universal Coordinated Time (UTC).
     * @param date A numeric value equal to the day of the month.
     */
    fun setUTCDate(date: number): number

    /**
     * Sets the month value in the Date object using local time.
     * @param month A numeric value equal to the month. The value for January is 0, and other month values follow consecutively.
     * @param date A numeric value representing the day of the month. If this value is not supplied, the value from a call to the getDate method is used.
     */
    fun setMonth(month: number, date?: number): number
    /**
     * Sets the month value in the Date object using Universal Coordinated Time (UTC).
     * @param month A numeric value equal to the month. The value for January is 0, and other month values follow consecutively.
     * @param date A numeric value representing the day of the month. If it is not supplied, the value from a call to the getUTCDate method is used.
     */
    fun setUTCMonth(month: number, date?: number): number
    /**
     * Sets the year of the Date object using local time.
     * @param year A numeric value for the year.
     * @param month A zero-based numeric value for the month (0 for January, 11 for December). Must be specified if numDate is specified.
     * @param date A numeric value equal for the day of the month.
     */
    fun setFullYear(year: number, month?: number, date?: number): number
    /**
     * Sets the year value in the Date object using Universal Coordinated Time (UTC).
     * @param year A numeric value equal to the year.
     * @param month A numeric value equal to the month. The value for January is 0, and other month values follow consecutively. Must be supplied if numDate is supplied.
     * @param date A numeric value equal to the day of the month.
     */
    fun setUTCFullYear(year: number, month?: number, date?: number): number
    /** Returns a date converted to a string using Universal Coordinated Time (UTC). */
    fun toUTCString(): string

    /** Returns a date as a string value in ISO format. */
    fun toISOString(): string

    /** Used by the JSON.stringify method to enable the transformation of an object's data for JavaScript Object Notation (JSON) serialization. */
    fun toJSON(key?: any): string
    /**
     * Converts a date and time to a string by using the current or specified locale.
     * @param locales A locale string, array of locale strings, Intl.Locale object, or array of Intl.Locale objects that contain one or more language or locale tags. If you include more than one locale string, list them in descending order of priority so that the first entry is the preferred locale. If you omit this parameter, the default locale of the JavaScript runtime is used.
     * @param options An object that contains one or more properties that specify comparison options.
     */
    fun toLocaleString(locales?: Intl.LocalesArgument, options?: Intl.DateTimeFormatOptions): string
    /**
     * Converts a date to a string by using the current or specified locale.
     * @param locales A locale string, array of locale strings, Intl.Locale object, or array of Intl.Locale objects that contain one or more language or locale tags. If you include more than one locale string, list them in descending order of priority so that the first entry is the preferred locale. If you omit this parameter, the default locale of the JavaScript runtime is used.
     * @param options An object that contains one or more properties that specify comparison options.
     */
    fun toLocaleDateString(locales?: Intl.LocalesArgument, options?: Intl.DateTimeFormatOptions): string
    /**
     * Converts a time to a string by using the current or specified locale.
     * @param locales A locale string, array of locale strings, Intl.Locale object, or array of Intl.Locale objects that contain one or more language or locale tags. If you include more than one locale string, list them in descending order of priority so that the first entry is the preferred locale. If you omit this parameter, the default locale of the JavaScript runtime is used.
     * @param options An object that contains one or more properties that specify comparison options.
     */
    fun toLocaleTimeString(locales?: Intl.LocalesArgument, options?: Intl.DateTimeFormatOptions): string

    companion object {
        /**
         * Parses a string containing a date, and returns the number of milliseconds between that date and midnight, January 1, 1970.
         * @param s A date string
         */
        fun parse(s: string): number

        /**
         * Returns the number of milliseconds between midnight, January 1, 1970 Universal Coordinated Time (UTC) (or GMT) and the specified date.
         * @param year The full year designation is required for cross-century date accuracy. If year is between 0 and 99 is used, then year is assumed to be 1900 + year.
         * @param monthIndex The month as a number between 0 and 11 (January to December).
         * @param date The date as a number between 1 and 31.
         * @param hours Must be supplied if minutes is supplied. A number from 0 to 23 (midnight to 11pm) that specifies the hour.
         * @param minutes Must be supplied if seconds is supplied. A number from 0 to 59 that specifies the minutes.
         * @param seconds Must be supplied if milliseconds is supplied. A number from 0 to 59 that specifies the seconds.
         * @param ms A number from 0 to 999 that specifies the milliseconds.
         */
        fun UTC(
            year: number,
            monthIndex: number,
            date?: number, hours?: number, minutes?: number, seconds?: number, ms?: number): number
        /** Returns the number of milliseconds elapsed since midnight, January 1, 1970 Universal Coordinated Time (UTC). */
        fun now(): number
    }
}
