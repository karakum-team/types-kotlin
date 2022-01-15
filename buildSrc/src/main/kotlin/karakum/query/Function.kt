package karakum.query

fun Function(
    source: String,
): Method =
    Method(
        source = source
            .removePrefix("export declare function ")
            .replace("{ queryClient, broadcastChannel, }:", "options:")
            .replace("{ storage, key, throttleTime, }:", "options:")
            .removeSuffix(";"),
        external = true,
    )
