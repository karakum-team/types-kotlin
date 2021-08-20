package com.github.turansky.react

import java.io.File

private const val HEADER = """
// $GENERATOR_COMMENT

package react.dom

import kotlinx.html.CommonAttributeGroupFacade
import org.w3c.dom.events.Event
"""

fun generateKotlinAdapter(
    definitionsFile: File,
    targetFile: File,
) {
    val source = definitionsFile.readText()
        .substringAfter("\n    interface DOMAttributes<", "")
        .substringAfter("} | undefined;\n", "")
        .substringBefore("\n    }", "")
        .trimIndent()

    val adapters = mutableListOf<String>()
    var previousName = ""

    adapters.add(HEADER)

    for (line in source.splitToSequence("\n")) {
        if (line.startsWith("// ")) {
            adapters.add(line)
            previousName = ""
            continue
        }

        if (!line.startsWith("on")) {
            continue
        }

        var (name, type) = line
            .substringBefore(";")
            .removeSuffix("<T> | undefined")
            .split("?: ")
        if (name == previousName + "Capture")
            continue

        previousName = name

        if (name == "onChange")
            type = "ChangeEventHandler"

        adapters.add(
            """
            var CommonAttributeGroupFacade.$name: $type<*>
                @Deprecated(message = "Write-only property", level = DeprecationLevel.HIDDEN)
                get() = error("")
                set(newValue) {
                    onEvent("${name.toLowerCase()}", newValue)
                }
            """.trimIndent()
        )
    }

    adapters.add(
        """
        private fun CommonAttributeGroupFacade.onEvent(
            type: String,
            handler: EventHandler<*>,
        ) {
            consumer.onTagEvent(
                tag = this,
                event = type,
                value = handler.unsafeCast<(Event) -> Unit>(),
            )
        }
        """.trimIndent()
    )

    targetFile.writeText(adapters.joinToString("\n\n").removePrefix("\n"))
}
