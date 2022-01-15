import karakum.router.generateKotlinDeclarations

tasks {
    named<Delete>("clean") {
        delete("src")
    }

    val generateDeclarations by registering {
        dependsOn(":kotlinNpmInstall")

        doLast {
            val sourceDir = projectDir.resolve("src/main/kotlin")

            delete(sourceDir)

            val nodeModules = rootProject.buildDir.resolve("js/node_modules")
            val historyFile = nodeModules.resolve("history/index.d.ts")
            val routerFile = nodeModules.resolve("react-router/index.d.ts")
            val routerDomFile = nodeModules.resolve("react-router-dom/index.d.ts")

            generateKotlinDeclarations(
                historyFile = historyFile,
                routerFile = routerFile,
                routerDomFile = routerDomFile,
                sourceDir = sourceDir,
            )
        }
    }

    named("compileKotlinJsLegacy") {
        dependsOn(generateDeclarations)
    }

    named("compileKotlinJsIr") {
        dependsOn(generateDeclarations)
    }
}
