plugins {
    alias(libs.plugins.kfc.library)
    alias(libs.plugins.seskar)
    `react-virtual-declarations`
}

dependencies {
    jsMainImplementation(npmv("@tanstack/react-virtual"))

    jsMainImplementation(kotlinWrappers.browser)
    jsMainImplementation(kotlinWrappers.reactCore)
}

val syncWithWrappers by tasks.creating(SyncWrappers::class) {
    from(generatedDir)
    into(kotlinWrappersDir("kotlin-tanstack-virtual-core"))
}
