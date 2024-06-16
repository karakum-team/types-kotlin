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
    val generatedDir = project.layout.projectDirectory.dir("src/jsMain/kotlin")
    val kotlinWrappersDir = project.rootProject.layout.projectDirectory.dir("../kotlin-wrappers")
    val cssomCoreDir = kotlinWrappersDir.dir("kotlin-cssom-core/src/jsMain/generated")

    from(generatedDir) {
        include("web/cssom/**")
    }

    into(cssomCoreDir.asFile)
}
