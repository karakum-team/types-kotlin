package karakum.node

internal fun addOverrides(
    name: String,
    declaration: String,
    body: String,
): String {
    var result = body

    if (name != "IEventEmitter"
        && "EventEmitter" in declaration
        || name == "Readable"
        || name == "Writable"
    ) {
        sequenceOf(
            "addListener",
            "emit",
            "on",
            "once",
            "prependListener",
            "prependOnceListener",
            "removeListener",
        ).forEach {
            result = result.replace("fun  $it(event: String", "override fun $it(event: String")
        }
    }

    if (name == "DuplexOptions" || name == "TransformOptions") {
        sequenceOf(
            "construct",
            "read",
            "write",
            "final",
            "destroy",
        ).forEach {
            result = result.replace("val $it:", "override val $it:")
        }
    }

    return result
}
