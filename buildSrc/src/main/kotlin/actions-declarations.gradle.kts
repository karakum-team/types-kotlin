plugins {
    id("declarations")
}

tasks.named("generateDeclarations") {
    doLast {
        val sourceDir = projectDir.resolve("src/jsMain/kotlin")

        delete(sourceDir)

        val definitionsDir = rootProject.buildDir
            .resolve("js/node_modules/@actions")

        karakum.actions.generateKotlinDeclarations(
            definitionsDir = definitionsDir,
            sourceDir = sourceDir,
        )
    }
}
