import java.util.*

plugins {
    `kotlin-dsl`
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
    implementation(kotlin("serialization", version("kotlin")))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${version("kotlinx-serialization")}")
}
