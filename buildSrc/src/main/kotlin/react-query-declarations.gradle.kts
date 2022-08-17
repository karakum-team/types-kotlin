plugins {
    id("declarations")
}

tasks.named("generateDeclarations") {
    doLast {
        val tanstackDir = rootProject.buildDir
            .resolve("js/node_modules/@tanstack")

        val coreTypesDir = tanstackDir
            .resolve("query-core/build/types/src")

        val reactTypesDir = tanstackDir
            .resolve("react-query/build/types/src")

        val sourceDir = projectDir.resolve("src/main/kotlin")

        delete(sourceDir)

        karakum.query.generateKotlinDeclarations(
            coreTypesDir = coreTypesDir,
            reactTypesDir = reactTypesDir,
            sourceDir = sourceDir,
        )
    }
}
