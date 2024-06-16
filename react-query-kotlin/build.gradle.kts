plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    `react-query-declarations`
}

val reactQueryVersion = property("react-query.version") as String

dependencies {
    jsMainImplementation(npm("@tanstack/react-query", reactQueryVersion))

    jsMainImplementation(wrappers("web"))
    jsMainImplementation(wrappers("react-core"))

    jsMainImplementation(seskarCore())
}

val syncCoreWrappers by tasks.creating(SyncWrappers::class) {
    val generatedDir = project.layout.projectDirectory.dir("src/jsMain/kotlin")

    val kotlinWrappersDir = project.rootProject.layout.projectDirectory.dir("../kotlin-wrappers")
    val sourceDir = kotlinWrappersDir.dir("kotlin-tanstack-query-core/src/jsMain/generated")

    from(generatedDir) {
        include("tanstack/query/")
    }
    into(sourceDir.asFile)
}

val syncReactWrappers by tasks.creating(SyncWrappers::class) {
    val generatedDir = project.layout.projectDirectory.dir("src/jsMain/kotlin")

    val kotlinWrappersDir = project.rootProject.layout.projectDirectory.dir("../kotlin-wrappers")
    val sourceDir = kotlinWrappersDir.dir("kotlin-tanstack-react-query/src/jsMain/generated")

    from(generatedDir) {
        include("tanstack/react/")
    }
    into(sourceDir.asFile)
}

val syncWithWrappers by tasks.creating {
    dependsOn(syncCoreWrappers)
    dependsOn(syncReactWrappers)
}
