rootProject.name = "react-types-kotlin"

pluginManagement {
    plugins {
        val kotlinVersion = extra["kotlin.version"] as String
        kotlin("js") version kotlinVersion

        val kfcVersion = extra["kfc.version"] as String
        id("com.github.turansky.kfc.library") version kfcVersion
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

include("csstype-kotlin")
include("popper-kotlin")
include("react-kotlin")
include("react-router-dom-kotlin")
