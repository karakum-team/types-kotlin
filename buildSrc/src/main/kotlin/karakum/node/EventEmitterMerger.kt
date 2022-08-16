package karakum.node

internal fun mergeEventEmitters(
    source: Sequence<ConversionResult>,
): Sequence<ConversionResult> {
    var results = source.toList()
    results = results - results.first { it.name == "IEventEmitter" }

    val emitter = results.first { it.name == "EventEmitter" }
    val iemitter = results.first { it.name == "IEventEmitter" }

    val functions = iemitter.body.substringAfter("{")
        .substringBeforeLast("}")
        .splitToSequence("fun ")
        .drop(1)
        .map { "override fun " + it.substringBefore("\n/**").replace(" = definedExternally", "") }
        .joinToString("\n\n")

    val newEmitter = emitter.copy(
        body = emitter.body.replace(
            "companion object ",
            functions + "\n" + "companion object ",
        )
    )

    return results.asSequence()
        .minus(emitter)
        .plus(newEmitter)
}
