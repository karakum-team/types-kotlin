plugins {
    `java-gradle-plugin`
    kotlin("jvm") version "1.6.10"
}

repositories {
    mavenCentral()
}

dependencies {
}

gradlePlugin {
    val karakum by plugins.creating {
        id = "team.karakum.converter"
        implementationClass = "team.karakum.converter.gradle.plugin.KarakumConverterPlugin"
    }
}
