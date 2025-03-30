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

    commonMainImplementation(kotlinWrappers.browser)
}

val syncWithWrappers by tasks.registering(SyncWrappers::class) {
    from(commonGeneratedDir)
    into(kotlinWrappersCommonDir("kotlin-popperjs-core"))
}
