plugins {
    id("declarations")
}

tasks.named("generateDeclarations") {
    doLast {
        val sourceDir = projectDir.resolve("src/main/kotlin")

        delete(sourceDir)

        /*
        karakum.browser.generateKotlinDeclarations(
            sourceDir = sourceDir,
        )
        */
    }
}
