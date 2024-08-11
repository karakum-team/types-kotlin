plugins {
    kfc("library")
    seskar()
    `csstype-declarations`
}

dependencies {
    jsMainImplementation(npmv("csstype"))

    jsMainImplementation(libs.wrappers.js)
}

val syncWithWrappers by tasks.creating(SyncWrappers::class) {
    from(generatedDir) {
        include("web/cssom/**")
    }

    into(kotlinWrappersDir("kotlin-cssom-core"))
}
