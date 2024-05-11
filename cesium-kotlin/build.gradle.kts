plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    `cesium-declarations`
}

dependencies {
    jsMainImplementation(npmv("@cesium/engine"))
    jsMainImplementation(npmv("@cesium/widgets"))

    jsMainImplementation(wrappers("browser"))

    jsMainImplementation(seskarCore())
}

val syncCesiumEngine by tasks.creating(Sync::class) {
    val generatedDir = project.layout.projectDirectory.dir("src/jsMain/kotlin")

    val kotlinWrappersDir = project.rootProject.layout.projectDirectory.dir("../kotlin-wrappers")
    val sourceDir = kotlinWrappersDir.dir("kotlin-cesium-engine/src/jsMain/generated")

    from(generatedDir) {
        include("cesium/engine/")
    }
    into(sourceDir.asFile)
}

val syncCesiumWidgets by tasks.creating(Sync::class) {
    val generatedDir = project.layout.projectDirectory.dir("src/jsMain/kotlin")

    val kotlinWrappersDir = project.rootProject.layout.projectDirectory.dir("../kotlin-wrappers")
    val sourceDir = kotlinWrappersDir.dir("kotlin-cesium-widgets/src/jsMain/generated")

    from(generatedDir) {
        include("cesium/widgets/")
    }
    into(sourceDir.asFile)
}

val syncWithWrappers by tasks.creating {
    dependsOn(syncCesiumEngine)
    dependsOn(syncCesiumWidgets)
}
