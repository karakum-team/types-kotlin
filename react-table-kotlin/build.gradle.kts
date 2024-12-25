plugins {
    alias(kfc.plugins.library)
    alias(libs.plugins.seskar)
    `react-table-declarations`
}

dependencies {
    jsMainImplementation(npmv("@tanstack/react-table"))

    jsMainImplementation(kotlinWrappers.browser)
    jsMainImplementation(kotlinWrappers.reactCore)
}

val syncWithWrappers by tasks.registering(SyncWrappers::class) {
    from(generatedDir) {
        include("tanstack/table/")
    }
    into(kotlinWrappersDir("kotlin-tanstack-table-core"))
}
