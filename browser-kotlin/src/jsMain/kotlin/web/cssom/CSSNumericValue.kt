// Automatically generated - do not modify!

package web.cssom

import js.core.ReadonlyArray

sealed external class CSSNumericValue :
    CSSStyleValue {
    fun add(...values: ReadonlyArray<CSSNumberish>): CSSNumericValue
    fun div(...values: ReadonlyArray<CSSNumberish>): CSSNumericValue
    fun equals(...value: ReadonlyArray<CSSNumberish>): Boolean
    fun max(...values: ReadonlyArray<CSSNumberish>): CSSNumericValue
    fun min(...values: ReadonlyArray<CSSNumberish>): CSSNumericValue
    fun mul(...values: ReadonlyArray<CSSNumberish>): CSSNumericValue
    fun sub(...values: ReadonlyArray<CSSNumberish>): CSSNumericValue
    fun to(unit: String): CSSUnitValue
    fun toSum(...units: ReadonlyArray<String>): CSSMathSum
    fun type(): CSSNumericType

    companion object {
        fun parse(cssText: String): CSSNumericValue
    }
}
