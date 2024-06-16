plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    `typescript-declarations`
}

kotlin {
    sourceSets.configureEach {
        languageSettings.optIn("kotlin.contracts.ExperimentalContracts")
    }
}

dependencies {
    jsMainImplementation(npmv("typescript"))

    jsMainImplementation(wrappers("js"))
    jsMainImplementation(seskarCore())
}

val syncWithWrappers by tasks.creating(SyncWrappers::class) {
    val generatedDir = project.layout.projectDirectory.dir("src/jsMain/kotlin")

    val kotlinWrappersDir = project.rootProject.layout.projectDirectory.dir("../kotlin-wrappers")
    val typescriptDir = kotlinWrappersDir.dir("kotlin-typescript/src/jsMain/generated")

    from(generatedDir)
    into(typescriptDir.asFile)
}
