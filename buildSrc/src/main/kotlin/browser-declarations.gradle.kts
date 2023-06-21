plugins {
    id("declarations")
}

tasks.named("generateDeclarations") {
    doLast {
        val sourceDir = projectDir.resolve("src/jsMain/kotlin")

        delete(sourceDir)

        val definitionsDir = rootProject.buildDir
            .resolve("js/node_modules/typescript")
            .resolve("lib")

        val webDefinitionsDir = rootProject.buildDir
            .resolve("js/node_modules/@types/web")

        val serviceworkerDefinitionsDir = rootProject.buildDir
            .resolve("js/node_modules/@types/serviceworker")

        karakum.browser.generateKotlinDeclarations(
            definitionsDir = definitionsDir,
            webDefinitionsFile = webDefinitionsDir.resolve("index.d.ts"),
            webIterableDefinitionsFile = webDefinitionsDir.resolve("iterable.d.ts"),
            serviceworkerDefinitionsFile = serviceworkerDefinitionsDir.resolve("index.d.ts"),
            serviceworkerIterableDefinitionsFile = serviceworkerDefinitionsDir.resolve("iterable.d.ts"),
            sourceDir = sourceDir,
        )
    }
}
