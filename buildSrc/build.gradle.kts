import java.util.*

plugins {
    `kotlin-dsl`
    // TEMP
    kotlin("plugin.serialization") version "2.1.10"
}

repositories {
    mavenCentral()
}

val props = Properties().apply {
    file("../gradle.properties").inputStream().use { load(it) }
}

fun version(target: String): String =
    props.getProperty("${target}.version")

dependencies {
    compileOnly(kotlin("gradle-plugin"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${version("kotlinx-serialization")}")
}
