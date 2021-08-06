import com.github.turansky.react.generateKotlinDeclarations

tasks {
    named<Delete>("clean") {
        delete("src")
    }

    val generateDeclarations by registering {
        dependsOn(":kotlinNpmInstall")

        doLast {
            val definitionsFile = rootProject.buildDir
                .resolve("js/node_modules/@types/react/index.d.ts")
            val sourceDir = projectDir.resolve("src/main/kotlin")

            delete(sourceDir)

            generateKotlinDeclarations(
                definitionsFile = definitionsFile,
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
