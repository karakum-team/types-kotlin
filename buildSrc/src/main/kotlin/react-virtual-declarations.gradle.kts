plugins {
    id("declarations")
}

tasks.named("generateDeclarations") {
    doLast {
        val coreDefinitionsFile = rootProject.buildDir
            .resolve("js/node_modules/@tanstack/virtual-core/build/lib/index.d.ts")

        val sourceDir = projectDir.resolve("src/jsMain/kotlin")

        delete(sourceDir)

        karakum.virtual.generateKotlinDeclarations(
            coreDefinitionsFile = coreDefinitionsFile,
            sourceDir = sourceDir,
        )
    }
}
