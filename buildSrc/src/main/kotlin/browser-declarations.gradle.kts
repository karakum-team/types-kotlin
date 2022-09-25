plugins {
    id("declarations")
}

tasks.named("generateDeclarations") {
    doLast {
        val sourceDir = projectDir.resolve("src/main/kotlin")

        delete(sourceDir)

        val definitionsFile = rootProject.buildDir
            .resolve("js/node_modules/typescript")
            .resolve("lib/lib.dom.d.ts")

        val webrtcDefinitionsFile = rootProject.buildDir
            .resolve("js/node_modules/@types/webrtc")

        karakum.browser.generateKotlinDeclarations(
            definitionsFile = definitionsFile,
            sourceDir = sourceDir,
        )

        karakum.webrtc.generateKotlinDeclarations(
            definitionsDir = webrtcDefinitionsFile,
            sourceDir = sourceDir,
        )
    }
}
