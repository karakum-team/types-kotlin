plugins {
    alias(libs.plugins.kfc.library)
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

    jsMainImplementation(libs.wrappers.node)
    jsMainImplementation(libs.coroutines.core)
}

val syncWithWrappers by tasks.creating(SyncWrappers::class) {
    from(generatedDir)
    into(kotlinWrappersDir("kotlin-actions-toolkit"))
}
