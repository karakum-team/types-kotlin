import karakum.cesium.generateKotlinDeclarations

tasks {
    named<Delete>("clean") {
        delete("src")
    }

    val sourceDir = file("src/main/kotlin")
    val remoteProjectDir = rootDir.parentFile.resolve("cesium-declarations/${project.name}")
    val remoteSourceDir = remoteProjectDir.resolve("src/main/kotlin")

    val generateDeclarations by registering {
        dependsOn(":kotlinNpmInstall")

        doLast {
            delete(sourceDir)

            val definitionsFile = rootProject.buildDir
                .resolve("js/node_modules/cesium")
                .resolve("Source/Cesium.d.ts")

            generateKotlinDeclarations(
                definitionsFile = definitionsFile,
                sourceDir = sourceDir,
                nonModular = project.name.endsWith("-nm")
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

    named("compileKotlinJsLegacy") {
        dependsOn(generateDeclarations)
    }

    named("compileKotlinJsIr") {
        dependsOn(generateDeclarations)
        finalizedBy(updateRemote)
    }
}
