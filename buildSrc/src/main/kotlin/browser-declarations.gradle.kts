plugins {
    id("declarations")
}

tasks.named("generateDeclarations") {
    doLast {
        val sourceDir = projectDir.resolve("src/main/kotlin")

        delete(sourceDir)

        val definitionsDir = rootProject.buildDir
            .resolve("js/node_modules/typescript")
            .resolve("lib")

        karakum.browser.generateKotlinDeclarations(
            definitionsDir = definitionsDir,
            sourceDir = sourceDir,
        )
    }
}
