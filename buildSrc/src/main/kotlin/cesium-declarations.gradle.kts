import karakum.cesium.generateKotlinDeclarations

tasks {
    named<Delete>("clean") {
        delete("src")
    }

    val sourceDir = file("src/commonMain/kotlin")

    val generateDeclarations by registering {
        dependsOn(":kotlinNpmInstall")

        doLast {
            delete(sourceDir)

            generateKotlinDeclarations(
                engineDefinitionsFile = nodeModules.resolve("@cesium/engine/index.d.ts"),
                widgetsDefinitionsFile = nodeModules.resolve("@cesium/widgets/index.d.ts"),
                sourceDir = sourceDir,
            )
        }
    }

    named("compileKotlinJs") {
        dependsOn(generateDeclarations)
    }
}
