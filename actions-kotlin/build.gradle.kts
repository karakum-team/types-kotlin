plugins {
    alias(kfc.plugins.library)
    alias(libs.plugins.seskar)
    `actions-declarations`
}

dependencies {
    commonMainImplementation(npmv("@actions/artifact"))
    commonMainImplementation(npmv("@actions/cache"))
    commonMainImplementation(npmv("@actions/core"))
    commonMainImplementation(npmv("@actions/exec"))
    commonMainImplementation(npmv("@actions/glob"))
    commonMainImplementation(npmv("@actions/github"))
    commonMainImplementation(npmv("@actions/http-client"))
    commonMainImplementation(npmv("@actions/io"))
    commonMainImplementation(npmv("@actions/tool-cache"))

    jsMainImplementation(kotlinWrappers.node)
    jsMainImplementation(libs.coroutines.core)
}

val syncWithWrappers by tasks.registering(SyncWrappers::class) {
    from(jsGeneratedDir)
    into(kotlinWrappersDir("kotlin-actions-toolkit"))
}
