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

    named("compileKotlinJs") {
        dependsOn(generateDeclarations)
        finalizedBy(updateRemote)
    }
}
