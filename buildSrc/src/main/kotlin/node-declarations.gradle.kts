plugins {
    id("declarations")
}

tasks.named("generateDeclarations") {
    doLast {
        val definitionsFile = rootProject.buildDir
            .resolve("js/node_modules/@types/node")
        val sourceDir = projectDir.resolve("src/main/kotlin")

        delete(sourceDir)

        karakum.node.generateKotlinDeclarations(
            definitionsDir = definitionsFile,
            sourceDir = sourceDir,
        )
    }
}
