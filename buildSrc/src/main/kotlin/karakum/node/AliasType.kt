package karakum.node

private val ALIAS_TYPE_MAP = mapOf(
    "LookupOptions['hints']" to "Int",
    "BaseOptions['columnOffset']" to "Number",
    "BaseOptions['lineOffset']" to "Number",
    "CreateContextOptions['codeGeneration']" to "Any",
    "CreateContextOptions['microtaskMode']" to "String",
    "CreateContextOptions['name']" to "String",
    "CreateContextOptions['origin']" to "String",
    "RunningScriptOptions['breakOnSigint']" to "Boolean",
    "RunningScriptOptions['timeout']" to "Number",
    "ScriptOptions['cachedData']" to "Any",
    "ScriptOptions['importModuleDynamically']" to "(specifier: String, script: Script, importAssertions: Any) -> Module",
)

internal fun getAliasType(
    type: String,
): String? {
    val alias = ALIAS_TYPE_MAP[type]
        ?: return null

    return "$alias /* $type */"
}
