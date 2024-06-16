plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    `csstype-declarations`
}

dependencies {
    jsMainImplementation(npmv("csstype"))

    jsMainImplementation(wrappers("js"))

    jsMainImplementation(seskarCore())
}

val syncWithWrappers by tasks.creating(SyncWrappers::class) {
    from(generatedDir) {
        include("web/cssom/**")
    }

    into(kotlinWrappersDir("kotlin-cssom-core"))
}
