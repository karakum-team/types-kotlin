import org.jetbrains.kotlin.gradle.targets.js.npm.PublicPackageJsonTask

plugins {
    kotlin("js") version "1.6.10"
}

repositories {
    mavenCentral()
}

val embeddedJsLibrary by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = false
}

dependencies {
}

kotlin.js {
    moduleName = project.name

    nodejs()
    useCommonJs()
    binaries.executable()
}

val productionExecutableCompileSync = tasks.named<Copy>("productionExecutableCompileSync")
val publicPackageJson = tasks.named<PublicPackageJsonTask>("publicPackageJson")

artifacts {
    add(embeddedJsLibrary.name, productionExecutableCompileSync.get().destinationDir) {
        builtBy(productionExecutableCompileSync)
    }
    add(embeddedJsLibrary.name, publicPackageJson.get().packageJsonFile) {
        builtBy(publicPackageJson)
    }
}
