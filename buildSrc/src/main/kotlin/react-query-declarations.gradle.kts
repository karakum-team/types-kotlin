plugins {
    id("declarations")
}

tasks.named("generateDeclarations") {
    doLast {
        val typesDir = rootProject.buildDir
            .resolve("js/node_modules/react-query/types")
        val sourceDir = projectDir.resolve("src/main/kotlin")

        delete(sourceDir)

        karakum.query.generateKotlinDeclarations(
            typesDir = typesDir,
            sourceDir = sourceDir,
        )
    }
}
