plugins {
    id("declarations")
}

tasks.named("generateDeclarations") {
    doLast {
        val coreDefinitionsDir = rootProject.buildDir
            .resolve("js/node_modules/@tanstack/table-core/build/lib")

        val sourceDir = projectDir.resolve("src/jsMain/kotlin")

        delete(sourceDir)

        karakum.table.generateKotlinDeclarations(
            coreDefinitionsDir = coreDefinitionsDir,
            sourceDir = sourceDir,
        )
    }
}
