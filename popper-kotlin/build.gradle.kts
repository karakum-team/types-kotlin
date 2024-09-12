plugins {
    alias(libs.plugins.kfc.library)
    alias(libs.plugins.seskar)
    `popper-declarations`
}

dependencies {
    jsMainImplementation(npmv("@popperjs/core"))

    jsMainImplementation(kotlinWrappers.browser)
}

val syncWithWrappers by tasks.creating(SyncWrappers::class) {
    from(generatedDir)
    into(kotlinWrappersDir("kotlin-popper"))
}
