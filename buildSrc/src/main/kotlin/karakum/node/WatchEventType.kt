package karakum.node

import karakum.common.unionBody

private val VALUES = listOf(
    "rename",
    "change",
)

// TODO: remove
internal fun WatchEventType(): ConversionResult =
    ConversionResult(
        "WatchEventType",
        unionBody("WatchEventType", VALUES)
    )
