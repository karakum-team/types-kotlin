tasks {
    named<Delete>("clean") {
        delete("src")
    }

    val generateDeclarations by registering {
        dependsOn(":kotlinNpmInstall")

        doLast {
            val definitionsDir = rootProject.buildDir
                .resolve("js/node_modules/@popperjs/core/lib")
            val sourceDir = projectDir.resolve("src/main/kotlin")

            delete(sourceDir)

            karakum.popper.generateKotlinDeclarations(
                definitionsDir = definitionsDir,
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
