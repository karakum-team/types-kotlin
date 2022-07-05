rootProject.name = "types-kotlin"

pluginManagement {
    plugins {
        val kotlinVersion = extra["kotlin.version"] as String
        kotlin("js") version kotlinVersion

        val kfcVersion = extra["kfc.version"] as String
        id("io.github.turansky.kfc.library") version kfcVersion

        val seskarVersion = extra["seskar.version"] as String
        id("io.github.turansky.seskar") version seskarVersion
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

includeBuild("karakum")

include("browser-kotlin")
include("cesium-kotlin")
include("cesium-kotlin-nm")
include("csstype-kotlin")
include("node-kotlin")
include("popper-kotlin")
include("react-kotlin")
include("react-query-kotlin")
include("react-router-dom-kotlin")
include("react-table-kotlin")
include("typescript-kotlin")
include("webrtc-kotlin")
