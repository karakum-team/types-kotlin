tasks {
    named<Delete>("clean") {
        delete("src")
    }

    val generateDeclarations by registering {
        dependsOn(":kotlinNpmInstall")
    }

    named("compileKotlinJs") {
        dependsOn(generateDeclarations)
    }
}
