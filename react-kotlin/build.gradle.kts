plugins {
    alias(libs.plugins.kfc.library)
    alias(libs.plugins.seskar)
    `react-declarations`
}

dependencies {
    jsMainImplementation(npmv("@types/react"))

    jsMainImplementation(libs.wrappers.browser)
    jsMainImplementation(libs.wrappers.react.core)
}

val syncWithWrappers by tasks.creating(SyncWrappers::class) {
    from(generatedDir)
    into(kotlinWrappersDir("kotlin-react-dom"))
}
