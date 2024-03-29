import karakum.cesium.generateKotlinDeclarations

tasks {
    named<Delete>("clean") {
        delete("src")
    }

    val sourceDir = file("src/jsMain/kotlin")
    val remoteProjectDir = rootDir.parentFile.resolve("cesium-kotlin/${project.name}")
    val remoteSourceDir = remoteProjectDir.resolve("src/jsMain/kotlin")

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

    val cleanRemote by registering(Delete::class) {
        onlyIf {
            remoteProjectDir.exists()
        }

        delete(remoteProjectDir.resolve("src"))
    }

    val updateRemote by registering(Copy::class) {
        onlyIf {
            remoteProjectDir.exists()
        }

        from(sourceDir)
        into(remoteSourceDir)

        dependsOn(cleanRemote)
    }

    named("compileKotlinJs") {
        dependsOn(generateDeclarations)
        finalizedBy(updateRemote)
    }
}
