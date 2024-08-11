plugins {
    kfc("library")
    seskar()
    `popper-declarations`
}

val popperVersion = property("popper.version") as String

dependencies {
    jsMainImplementation(npm("@popperjs/core", popperVersion))

    jsMainImplementation(libs.wrappers.browser)
}

val syncWithWrappers by tasks.creating(SyncWrappers::class) {
    from(generatedDir)
    into(kotlinWrappersDir("kotlin-popper"))
}
