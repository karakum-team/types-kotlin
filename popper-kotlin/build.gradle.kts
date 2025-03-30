plugins {
    alias(kfc.plugins.library)
    alias(libs.plugins.seskar)
    `popper-declarations`
}

kotlin {
    compilerOptions {
        optIn.add("kotlin.ExperimentalStdlibApi")
    }
}

dependencies {
    commonMainImplementation(npmv("@popperjs/core"))

    jsMainImplementation(kotlinWrappers.browser)
}

val syncWithWrappers by tasks.registering(SyncWrappers::class) {
    from(jsGeneratedDir)
    into(kotlinWrappersDir("kotlin-popperjs-core"))
}
