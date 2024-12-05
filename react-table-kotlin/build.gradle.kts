plugins {
    alias(kfc.plugins.library)
    alias(libs.plugins.seskar)
    `react-table-declarations`
}

dependencies {
    jsMainImplementation(npmv("@tanstack/react-table"))

    jsMainImplementation(kotlinWrappers.browser)
    jsMainImplementation(kotlinWrappers.reactCore)

    // TODO: remove after migration on React 19
    jsMainImplementation(kotlinWrappers.reactDom)
}

val syncWithWrappers by tasks.creating(SyncWrappers::class) {
    from(generatedDir) {
        include("tanstack/table/")
    }
    into(kotlinWrappersDir("kotlin-tanstack-table-core"))
}
