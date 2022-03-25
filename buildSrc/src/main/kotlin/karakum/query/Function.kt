package karakum.query

fun Function(
    source: String,
): Method =
    Method(
        source = source
            .removePrefix("export declare function ")
            .replace(" extends (...args: any[]) => boolean", ": Function<Boolean>")
            .replace("{ queryClient, broadcastChannel, }:", "options:")
            .replace("{ storage, key, throttleTime, }:", "options:")
            .removeSuffix(";"),
        external = true,
    )
