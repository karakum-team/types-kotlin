plugins {
    id("declarations")
}

tasks.named("generateDeclarations") {
    doLast {
        val definitionsFile = rootProject.buildDir
            .resolve("js/node_modules/csstype/index.d.ts")
        val sourceDir = projectDir.resolve("src/main/kotlin")

        delete(sourceDir)

        karakum.csstype.generateKotlinDeclarations(
            definitionsFile = definitionsFile,
            sourceDir = sourceDir,
        )
    }
}
