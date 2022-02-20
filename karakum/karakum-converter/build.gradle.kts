import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

plugins {
    kotlin("js") version "1.6.10"
}

repositories {
    mavenCentral()
}

val executableJs by configurations.creating {
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

val compileProductionExecutableKotlinJs = tasks.named<Kotlin2JsCompile>("compileProductionExecutableKotlinJs")

artifacts {
    add("executableJs", compileProductionExecutableKotlinJs.get().outputFileProperty) {
        builtBy(compileProductionExecutableKotlinJs)
    }
}
