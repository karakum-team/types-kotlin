plugins {
    alias(kfc.plugins.library)
    alias(libs.plugins.seskar)
    `react-virtual-declarations`
}

dependencies {
    jsMainImplementation(npmv("@tanstack/react-virtual"))

    jsMainImplementation(kotlinWrappers.browser)
    jsMainImplementation(kotlinWrappers.reactCore)

    // TODO: remove after migration on React 19
    jsMainImplementation(kotlinWrappers.reactDom)
}

val syncWithWrappers by tasks.creating(SyncWrappers::class) {
    from(generatedDir)
    into(kotlinWrappersDir("kotlin-tanstack-virtual-core"))
}
