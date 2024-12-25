plugins {
    alias(kfc.plugins.library)
    alias(libs.plugins.seskar)
    `react-virtual-declarations`
}

dependencies {
    jsMainImplementation(npmv("@tanstack/react-virtual"))

    jsMainImplementation(kotlinWrappers.browser)
    jsMainImplementation(kotlinWrappers.reactCore)
}

val syncWithWrappers by tasks.registering(SyncWrappers::class) {
    from(generatedDir)
    into(kotlinWrappersDir("kotlin-tanstack-virtual-core"))
}
