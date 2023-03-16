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

        val webDefinitionsFile = rootProject.buildDir
            .resolve("js/node_modules/@types/web")
            .resolve("index.d.ts")

        val serviceworkerDefinitionsFile = rootProject.buildDir
            .resolve("js/node_modules/@types/serviceworker")
            .resolve("index.d.ts")

        karakum.browser.generateKotlinDeclarations(
            definitionsDir = definitionsDir,
            webDefinitionsFile = webDefinitionsFile,
            serviceworkerDefinitionsFile = serviceworkerDefinitionsFile,
            sourceDir = sourceDir,
        )
    }
}
