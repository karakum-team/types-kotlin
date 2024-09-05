plugins {
    alias(libs.plugins.kfc.library)
    alias(libs.plugins.seskar)
    `react-virtual-declarations`
}

dependencies {
    jsMainImplementation(npmv("@tanstack/react-virtual"))

    jsMainImplementation(libs.wrappers.browser)
    jsMainImplementation(libs.wrappers.reactCore)
}

val syncWithWrappers by tasks.creating(SyncWrappers::class) {
    from(generatedDir)
    into(kotlinWrappersDir("kotlin-tanstack-virtual-core"))
}
