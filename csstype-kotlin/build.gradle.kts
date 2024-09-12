plugins {
    alias(kfc.plugins.library)
    alias(libs.plugins.seskar)
    `csstype-declarations`
}

dependencies {
    jsMainImplementation(npmv("csstype"))

    jsMainImplementation(kotlinWrappers.js)
}

val syncWithWrappers by tasks.creating(SyncWrappers::class) {
    from(generatedDir) {
        include("web/cssom/**")
    }

    into(kotlinWrappersDir("kotlin-cssom-core"))
}
