plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    `cesium-declarations`
}

dependencies {
    jsMainImplementation(npmv("cesium"))

    jsMainImplementation(wrappers("browser"))
}

val syncWithWrappers by tasks.creating(Sync::class) {
    val generatedDir = project.layout.projectDirectory.dir("src/jsMain/kotlin")

    val kotlinWrappersDir = project.rootProject.layout.projectDirectory.dir("../kotlin-wrappers")
    val typescriptDir = kotlinWrappersDir.dir("kotlin-cesium/src/jsMain/generated")

    from(generatedDir)
    into(typescriptDir.asFile)
}
