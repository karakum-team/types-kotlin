plugins {
    id("declarations")
}

tasks.named("generateDeclarations") {
    doLast {
        val definitionsFile = rootProject.buildDir
            .resolve("js/node_modules/@types/webrtc/RTCPeerConnection.d.ts")
        val sourceDir = projectDir.resolve("src/main/kotlin")

        delete(sourceDir)

        karakum.webrtc.generateKotlinDeclarations(
            definitionsFile = definitionsFile,
            sourceDir = sourceDir,
        )
    }
}
