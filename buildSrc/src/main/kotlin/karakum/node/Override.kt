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

    if (name == "Duplex") {
        result = result
            .replace("val closed:", "override val closed:")
            .replace("val errored:", "override val errored:")
            .replace("fun  _destroy(", "override fun _destroy(")
    }

    if (name == "Readable") {
        result = result
            .replace("var readable:", "override var readable:")
            .replace("fun  read(", "override fun read(")
            .replace("fun  setEncoding(", "override fun setEncoding(")
            .replace("fun  pause(", "override fun pause(")
            .replace("fun  resume(", "override fun resume(")
            .replace("fun  isPaused(", "override fun isPaused(")
            .replace("fun  unpipe(", "override fun unpipe(")
            .replace("fun  unshift(", "override fun unshift(")
            .replace("fun  wrap(", "override fun wrap(")

            .replace("val closed:", "open val closed:")
            .replace("val errored:", "open val errored:")
            .replace("fun  _destroy(", "open fun _destroy(")
    }

    if (name == "Writable") {
        result = result
            .replace("val writable:", "override val writable:")
            .replaceFirst("fun  write(", "override fun write(")
            .replaceFirst("fun  end(", "override fun end(")
    }

    return result
}
