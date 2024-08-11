plugins {
    kfc("library")
    seskar()
    `react-virtual-declarations`
}

val reactVirtualVersion = property("react-virtual.version") as String

dependencies {
    jsMainImplementation(npm("@tanstack/react-virtual", reactVirtualVersion))

    jsMainImplementation(libs.wrappers.browser)
    jsMainImplementation(libs.wrappers.react.core)
}

val syncWithWrappers by tasks.creating(SyncWrappers::class) {
    from(generatedDir)
    into(kotlinWrappersDir("kotlin-tanstack-virtual-core"))
}
