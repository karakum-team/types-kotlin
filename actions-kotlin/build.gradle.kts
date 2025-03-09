plugins {
    alias(kfc.plugins.library)
    alias(libs.plugins.seskar)
    `actions-declarations`
}

dependencies {
    jsMainImplementation(npmv("@actions/artifact"))
    jsMainImplementation(npmv("@actions/cache"))
    jsMainImplementation(npmv("@actions/core"))
    jsMainImplementation(npmv("@actions/exec"))
    jsMainImplementation(npmv("@actions/glob"))
    jsMainImplementation(npmv("@actions/github"))
    jsMainImplementation(npmv("@actions/http-client"))
    jsMainImplementation(npmv("@actions/io"))
    jsMainImplementation(npmv("@actions/tool-cache"))

    jsMainImplementation(kotlinWrappers.node)
    jsMainImplementation(libs.coroutines.core)
}

val syncWithWrappers by tasks.registering(SyncWrappers::class) {
    from(jsGeneratedDir)
    into(kotlinWrappersDir("kotlin-actions-toolkit"))
}
