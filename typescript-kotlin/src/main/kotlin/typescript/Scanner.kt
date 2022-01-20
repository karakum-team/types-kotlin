// Automatically generated - do not modify!

package typescript

external interface Scanner {
    fun getStartPos(): Double
    fun getToken(): SyntaxKind
    fun getTextPos(): Double
    fun getTokenPos(): Double
    fun getTokenText(): String
    fun getTokenValue(): String
    fun hasUnicodeEscape(): Boolean
    fun hasExtendedUnicodeEscape(): Boolean
    fun hasPrecedingLineBreak(): Boolean
    fun isIdentifier(): Boolean
    fun isReservedWord(): Boolean
    fun isUnterminated(): Boolean
    fun reScanGreaterToken(): SyntaxKind
    fun reScanSlashToken(): SyntaxKind
    fun reScanAsteriskEqualsToken(): SyntaxKind
    fun reScanTemplateToken(isTaggedTemplate: Boolean): SyntaxKind
    fun reScanTemplateHeadOrNoSubstitutionTemplate(): SyntaxKind
    fun scanJsxIdentifier(): SyntaxKind
    fun scanJsxAttributeValue(): SyntaxKind
    fun reScanJsxAttributeValue(): SyntaxKind
    fun reScanJsxToken(allowMultilineJsxText: Boolean = definedExternally): JsxTokenSyntaxKind
    fun reScanLessThanToken(): SyntaxKind
    fun reScanHashToken(): SyntaxKind
    fun reScanQuestionToken(): SyntaxKind
    fun reScanInvalidIdentifier(): SyntaxKind
    fun scanJsxToken(): JsxTokenSyntaxKind
    fun scanJsDocToken(): JSDocSyntaxKind
    fun scan(): SyntaxKind
    fun getText(): String
    fun setText(text: String?, start: Double = definedExternally, length: Double = definedExternally)
    fun setOnError(onError: ErrorCallback?)
    fun setScriptTarget(scriptTarget: ScriptTarget)
    fun setLanguageVariant(variant: LanguageVariant)
    fun setTextPos(textPos: Double)
    fun <T> lookAhead(callback: () -> T): T
    fun <T> scanRange(start: Double, length: Double, callback: () -> T): T
    fun <T> tryScan(callback: () -> T): T
}
