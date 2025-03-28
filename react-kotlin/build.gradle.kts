plugins {
    alias(kfc.plugins.library)
    alias(libs.plugins.seskar)
    `react-declarations`
}

dependencies {
    jsMainImplementation(npmv("@types/react"))

    jsMainImplementation(kotlinWrappers.browser)
    jsMainImplementation(kotlinWrappers.reactCore)
}

val syncWithWrappers by tasks.registering(SyncWrappers::class) {
    from(jsGeneratedDir)
    into(kotlinWrappersDir("kotlin-react-dom"))
}
