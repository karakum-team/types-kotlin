plugins {
    alias(kfc.plugins.library)
    alias(libs.plugins.seskar)
    `react-table-declarations`
}

dependencies {
    commonMainImplementation(npmv("@tanstack/react-table"))

    commonMainImplementation(kotlinWrappers.browser)
    jsMainImplementation(kotlinWrappers.reactCore)
}

val syncWithWrappers by tasks.registering(SyncWrappers::class) {
    from(jsGeneratedDir) {
        include("tanstack/table/")
    }
    into(kotlinWrappersDir("kotlin-tanstack-table-core"))
}
