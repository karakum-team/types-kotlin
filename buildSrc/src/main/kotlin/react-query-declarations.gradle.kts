tasks {
    named<Delete>("clean") {
        delete("src")
    }

    val generateDeclarations by registering {
        dependsOn(":kotlinNpmInstall")

        doLast {
            val typesDir = rootProject.buildDir
                .resolve("js/node_modules/react-query/types")
            val sourceDir = projectDir.resolve("src/main/kotlin")

            delete(sourceDir)

            karakum.query.generateKotlinDeclarations(
                typesDir = typesDir,
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
