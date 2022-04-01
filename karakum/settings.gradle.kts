rootProject.name = "karakum"

pluginManagement {
    plugins {
        val kotlinVersion = extra["kotlin.version"] as String
        kotlin("jvm") version kotlinVersion
        kotlin("js") version kotlinVersion
    }
}

include("karakum-converter")
include("karakum-gradle-plugin")
