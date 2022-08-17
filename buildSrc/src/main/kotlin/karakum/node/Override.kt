package karakum.node

internal val OPEN_CLASSES = setOf(
    "EventEmitter",
    "Server",
    "Socket",
    "Stream",
    "LegacyStream",
    "Duplex",
    "Readable",
    "Transform",
    "Writable",
    "OutgoingMessage",
)

private val EMITTER_METHODS = listOf(
    "addListener",
    "emit",
    "off",
    "on",
    "once",
    "prependListener",
    "prependOnceListener",
    "removeListener",
)

internal fun addOverrides(
    name: String,
    declaration: String,
    body: String,
    classMode: Boolean,
): String {
    var result = body

    if (name != "IEventEmitter"
        && "EventEmitter" in declaration
        || name == "Readable"
        || name == "Writable"
        || name == "Socket"
        || name == "WriteStream"
        || name == "ClientRequest"
        || (name == "Server" && "node.net.Server" in declaration)
    ) {
        EMITTER_METHODS.forEach {
            result = result.replace("fun  $it(event: String", "override fun $it(event: String")
        }
    }

    if (name == "Socket") {
        result = result.replace("val destroyed:", "override var /* val */ destroyed:")

        EMITTER_METHODS.forEach { method ->
            sequenceOf(
                "Event.END",
                "Event.ERROR",
            ).forEach { event ->
                result = result.replace(
                    "fun  $method(event: $event",
                    "override fun $method(event: $event",
                )
            }
        }
    }

    if (name == "Server" && "node.net.Server" in declaration) {
        EMITTER_METHODS.forEach { method ->
            sequenceOf(
                "Event.CLOSE",
                "Event.CONNECTION",
                "Event.ERROR",
                "Event.LISTENING",
            ).forEach { event ->
                result = result.replace(
                    "fun  $method(event: $event",
                    "override fun $method(event: $event",
                )
            }
        }
    }

    if (name == "ClientRequest") {
        EMITTER_METHODS.forEach { method ->
            sequenceOf(
                "Event.CLOSE",
                "Event.DRAIN",
                "Event.ERROR",
                "Event.FINISH",
                "Event.PIPE",
                "Event.UNPIPE",
            ).forEach { event ->
                result = result.replace(
                    "fun  $method(event: $event",
                    "override fun $method(event: $event",
                )
            }
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

    if (name == "Writable" || name == "Socket") {
        result = result
            .replace("val writable:", "override /* val */ var writable:")
            .replace("fun  write(", "override fun write(")
            .replace("\nfun  end(", "\noverride fun end(")

            // TODO: fix typings
            .replace("(buffer: Any /* ", "(chunk: Any /* ")
            .replace("(str: Any /* ", "(chunk: Any /* ")
            .replaceFirst(" write(chunk: Any,\nencoding: ", " write(str: String,\nencoding: ")
            .replaceFirst(" end(chunk: Any,\nencoding: ", " end(str: String,\nencoding: ")
    }

    if (name == "Writable" || name == "Socket") {
        if (name == "Socket")
            result = result.replaceFirst("callback: () -> Unit", "[callback: () -> Unit]")

        result = result.replace("callback: () -> Unit = definedExternally", "callback: () -> Unit")

        if (name == "Socket")
            result = result.replaceFirst("[callback: () -> Unit]", "callback: () -> Unit")
    }

    if (name == "Writable" || name == "Socket") {
        sequenceOf(
            "callback: (error: Error?) -> Unit",
            "callback: (error: Error?) -> Unit",
        ).forEach {
            result = result.replaceFirst("$it = definedExternally", it)
        }
    }

    if (name == "Socket") {
        result = result.replace(
            "encoding: node.buffer.BufferEncoding = definedExternally",
            "encoding: node.buffer.BufferEncoding",
        )
    }

    if (name == "BroadcastChannel") {
        result = """
            override fun  ref(): RefCounted
            override fun  unref(): RefCounted
            
        """ + result
    }

    if (classMode && name in OPEN_CLASSES) {
        result = ("\n" + result)
            .splitToSequence("companion object {")
            .mapIndexed { index, data ->
                if (index == 0) {
                    data.replace("\nval ", "\nopen val ")
                        .replace("\nvar ", "\nopen var ")
                        .replace("\nfun ", "\nopen fun ")
                } else data
            }
            .joinToString("companion object {")
            .removePrefix("\n")
    }

    return result
}
