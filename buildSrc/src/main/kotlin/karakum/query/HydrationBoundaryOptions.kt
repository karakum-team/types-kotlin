package karakum.query

internal val HYDRATION_BOUNDARY_OPTIONS_SOURCE = """
Omit<HydrateOptions, 'defaultOptions'> & {
        defaultOptions?: Omit<HydrateOptions['defaultOptions'], 'mutations'>;
    };
""".trimIndent()

internal val HYDRATION_BOUNDARY_OPTIONS_REPLACEMENT = HYDRATION_BOUNDARY_OPTIONS_SOURCE
    .replace("\n        ", " ")
    .replace("\n    ", " ")

internal val HYDRATION_BOUNDARY_OPTIONS = HYDRATION_BOUNDARY_OPTIONS_REPLACEMENT.removeSuffix(";")
