plugins {
    id("declarations")
}

tasks.named("generateDeclarations") {
    doLast {
        val definitionsFile = rootProject.buildDir
            .resolve("js/node_modules/@types/webrtc")
        val sourceDir = projectDir.resolve("src/main/kotlin")

        delete(sourceDir)

        karakum.webrtc.generateKotlinDeclarations(
            definitionsDir = definitionsFile,
            sourceDir = sourceDir,
        )
    }
}
