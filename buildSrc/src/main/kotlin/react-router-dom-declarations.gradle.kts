plugins {
    id("declarations")
}

tasks.named("generateDeclarations") {
    doLast {
        val sourceDir = projectDir.resolve("src/main/kotlin")

        delete(sourceDir)

        val nodeModules = rootProject.buildDir.resolve("js/node_modules")
        val historyFile = nodeModules.resolve("history/index.d.ts")
        val routerFiles = nodeModules.resolve("react-router/lib")
            .listFiles { file -> file.name.endsWith(".d.ts") }!!
        val routerDomFile = nodeModules.resolve("react-router-dom/index.d.ts")

        karakum.router.generateKotlinDeclarations(
            historyFile = historyFile,
            routerFiles = routerFiles,
            routerDomFile = routerDomFile,
            sourceDir = sourceDir,
        )
    }
}
