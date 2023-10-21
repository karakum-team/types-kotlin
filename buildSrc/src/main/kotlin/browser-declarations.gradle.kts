plugins {
    id("declarations")
}

tasks.named("generateDeclarations") {
    doLast {
        val sourceDir = projectDir.resolve("src/jsMain/kotlin")

        delete(sourceDir)

        val definitionsDir = nodeModules.resolve("typescript/lib")
        val webDefinitionsDir = nodeModules.resolve("@types/web")
        val serviceworkerDefinitionsDir = nodeModules.resolve("@types/serviceworker")

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
