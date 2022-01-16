tasks {
    named<Delete>("clean") {
        delete("src")
    }

    val generateDeclarations by registering {
        dependsOn(":kotlinNpmInstall")
    }

    named("compileKotlinJsLegacy") {
        dependsOn(generateDeclarations)
    }

    named("compileKotlinJsIr") {
        dependsOn(generateDeclarations)
    }
}
