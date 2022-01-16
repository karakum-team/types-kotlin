plugins {
    id("declarations")
}

tasks.named("generateDeclarations") {
    doLast {
        val definitionsDir = rootProject.buildDir
            .resolve("js/node_modules/@popperjs/core/lib")
        val sourceDir = projectDir.resolve("src/main/kotlin")

        delete(sourceDir)

        karakum.popper.generateKotlinDeclarations(
            definitionsDir = definitionsDir,
            sourceDir = sourceDir,
        )
    }
}
