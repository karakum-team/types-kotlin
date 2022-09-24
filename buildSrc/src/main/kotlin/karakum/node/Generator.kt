package karakum.node

import karakum.common.GENERATOR_COMMENT
import karakum.common.Suppress
import karakum.common.fileSuppress
import java.io.File

private val DEFAULT_IMPORTS = listOf(
    "Promise" to "kotlin.js.Promise",

    "AsyncIterable" to "kotlinx.js.AsyncIterable",
    "BigInt" to "kotlinx.js.BigInt",
    "JsIterable" to "kotlinx.js.JsIterable",
    "JsTuple2" to "kotlinx.js.JsTuple2",
    "JsSet" to "kotlinx.js.JsSet",
    "PromiseResult" to "kotlinx.js.PromiseResult",
    "Record" to "kotlinx.js.Record",
    "ReadonlyArray" to "kotlinx.js.ReadonlyArray",
    "Symbol" to "kotlinx.js.Symbol",
    "Void" to "kotlinx.js.Void",

    "Uint8Array" to "kotlinx.js.Uint8Array",
    "ArrayBuffer" to "kotlinx.js.ArrayBuffer",
    "ArrayBufferView" to "kotlinx.js.ArrayBufferView",

    ".await()" to "kotlinx.coroutines.await",

    "Dict<" to "node.Dict",
    "ReadOnlyDict<" to "node.ReadOnlyDict",
    "RefCounted" to "node.RefCounted",
    "AbortSignal" to "node.AbortSignal",
    "ErrnoException" to "node.ErrnoException",
    "Module" to "node.Module",
    PIPE_OPTIONS to "node.$PIPE_OPTIONS",

    "Buffer" to "node.buffer.Buffer",
    "BufferEncoding" to "node.buffer.BufferEncoding",
    ABORTABLE to "node.events.$ABORTABLE",
    "$EVENT." to "node.events.$EVENT",
    "$EVENT_TYPE" to "node.events.$EVENT_TYPE",

    "Readable" to "node.stream.Readable",
    "Writable" to "node.stream.Writable",
    "Duplex" to "node.stream.Duplex",
    "Pipe" to "node.stream.Pipe",
    "Transform" to "node.stream.Transform",
    "TransformOptions" to "node.stream.TransformOptions",
    "WritableOptions" to "node.stream.WritableOptions",

    "Signals" to "node.process.Signals",
    "ProcessEnv" to "node.process.ProcessEnv",
    "BufferEncodingOption" to "node.fs.BufferEncodingOption",
    "ObjectEncodingOptions" to "node.fs.ObjectEncodingOptions",

    "LookupFunction" to "node.net.LookupFunction",
    "Socket" to "node.net.Socket",
    "Worker" to "node.workerThreads.Worker",

    "ClientRequestArgs" to "node.http.ClientRequestArgs",
    "Context" to "node.vm.Context",
)

private val MODULES = setOf(
    "async_hooks",
    "buffer",
    "crypto",
    "child_process",
    "events",
    "globals",
    "fs",
    "fs/promises",
    "http",
    "inspector",
    "net",
    "os",
    "path",
    "process",
    "querystring",
    "stream",
    "stream/promises",
    "stream/web",
    "tty",
    "url",
    "util",
    "vm",
    "worker_threads",
)

private val GLOBAL_TYPES = setOf(
    ABORT_CONTROLLER,
    ABORT_SIGNAL,
    "Buffer",
)

fun generateKotlinDeclarations(
    definitionsDir: File,
    sourceDir: File,
) {
    for (module in MODULES) {
        val pkg = Package(module)
        val targetDir = sourceDir
            .resolve(pkg.path)
            .also { it.mkdirs() }

        val source = definitionsDir
            .resolve("$module.d.ts")
            .readText()
            .replace("(eventName: ", "(event: ")
            .replace(", eventName: ", ", event: ")
            .replace(", name: string | symbol", ", event: string | symbol")
            .replace(", cb:", ", callback:")
            .replace(", cb?:", ", callback?:")
            .replace("(cb:", "(callback:")
            .replace("(cb?:", "(callback?:")
            .replace("(err?:", "(error?:")
            .replace("(err:", "(error:")
            .replace(" wrap(oldStream:", " wrap(stream:")
            .replace(" write(buffer: Uint8Array | string", " write(chunk: Uint8Array | string")
            .replace(" end(data: string | Uint8Array", "end(chunk: string | Uint8Array")
            .replace(" & { req: InstanceType<Request> }", "")
            // TEMP
            .replace("headers: OutgoingHttpHeaders | ReadonlyArray<[string, string]>", "headers: OutgoingHttpHeaders")

        var definitions = convertDefinitions(source, pkg)
        when (pkg) {
            Package("events") -> definitions = definitions + Event(definitionsDir) + EventType()
            Package("inspector") -> definitions = definitions + inspectorEvents(definitionsDir)
            Package("test") -> definitions = definitions + testEvent(definitionsDir)
        }

        definitions = definitions
            .groupBy { it.name }
            .map { (name, group) ->
                group.singleOrNull()
                    ?: ConversionResult(
                        name = name,
                        body = group.joinToString("\n\n\n") { it.body },
                    )
            }
            .asSequence()

        for ((name, body) in definitions) {
            val suppresses = mutableListOf<Suppress>().apply {
                if ("JsName(\"\"\"(" in body || "JsName(\"'" in body)
                    add(Suppress.NAME_CONTAINS_ILLEGAL_CHARS)

                if ("JsName(\"\"\"(" in body && (name == "KeyFormat" || name == "KeyType"))
                    add(Suppress.NESTED_CLASS_IN_EXTERNAL_INTERFACE)

                if ("inline fun " in body)
                    add(Suppress.NOTHING_TO_INLINE)
            }.toTypedArray()

            val annotations = when {
                name in GLOBAL_TYPES -> ""

                "@JsModule(" in body -> ""

                "external class " in body
                        || "external open class " in body
                        || "external sealed class " in body
                        || "external abstract class " in body
                        || "external val " in body
                        || "external fun " in body
                -> "@file:JsModule(\"${pkg.id}\")\n@file:JsNonModule"

                suppresses.isNotEmpty()
                -> fileSuppress(*suppresses)

                else -> ""
            }

            val fileName = when (name) {
                "verify" -> "$name.fun.kt"
                else -> "$name.kt"
            }

            targetDir.resolve(fileName)
                .also { check(!it.exists()) }
                .writeText(
                    fileContent(
                        annotations = annotations,
                        body = body,
                        pkg = pkg,
                    )
                )
        }
    }
}

private fun fileContent(
    annotations: String = "",
    body: String,
    pkg: Package,
): String {
    val defaultImports = DEFAULT_IMPORTS
        .let { if (pkg.name != "process") it else it.filter { it.first != "Socket" } }
        .filter { it.first in body }
        .map { "import ${it.second}" }
        .joinToString("\n")

    var result = sequenceOf(
        "// $GENERATOR_COMMENT",
        annotations,
        pkg.pkg,
        defaultImports,
        body,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")

    if (!result.endsWith("\n"))
        result += "\n"

    return result
}
