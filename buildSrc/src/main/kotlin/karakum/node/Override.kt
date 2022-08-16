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
        || name == "Socket"
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
            result = result.replace("var $it:", "override var $it:")
        }
    }

    if (name == "Duplex") {
        result = result
            .replace("val closed:", "override val closed:")
            .replace("val errored:", "override val errored:")
            .replace("fun  _destroy(", "override fun _destroy(")
    }

    if (name == "Readable" || name == "Socket") {
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

    if (name == "Readable") {
        sequenceOf(
            "size: Number",
            "destination: node.WritableStream",
            "encoding: node.buffer.BufferEncoding",
        ).forEach {
            result = result.replaceFirst("$it = definedExternally", it)
        }
    }

    if (name == "Writable") {
        result = result
            .replace("val writable:", "override /* val */ var writable:")
            .replace("fun  write(", "override fun write(")
            .replace("\nfun  end(", "\noverride fun end(")

            // TODO: fix typings
            .replaceFirst(" write(chunk: Any,\nencoding: ", " write(str: String,\nencoding: ")
            .replaceFirst(" end(chunk: Any,\nencoding: ", " end(str: String,\nencoding: ")
    }

    if (name == "Writable") {
        sequenceOf(
            "callback: () -> Unit",
            "callback: () -> Unit",
            "callback: () -> Unit",
            "callback: (error: Error?) -> Unit",
            "callback: (error: Error?) -> Unit",
        ).forEach {
            result = result.replaceFirst("$it = definedExternally", it)
        }
    }

    return result
}
