package karakum.query

// language=Kotlin
internal val ALIASES_BODY = """
// $GENERATOR_COMMENT    

${Package.CORE.pkg}

typealias JsTimestamp = Double
typealias JsDuration = Int

typealias Union = Any

typealias Type = String  

typealias False = Boolean    
typealias True = Boolean

typealias Page = Any
typealias PageParam = Any
"""
