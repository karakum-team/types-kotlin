plugins {
    id("declarations")
}

tasks.named("generateDeclarations") {
    doLast {
        val definitionsFile = rootProject.buildDir
            .resolve("js/node_modules/typescript/lib/typescript.d.ts")
        val sourceDir = projectDir.resolve("src/jsMain/kotlin")

        delete(sourceDir)

        karakum.typescript.generateKotlinDeclarations(
            definitionsFile = definitionsFile,
            sourceDir = sourceDir,
        )
    }
}
