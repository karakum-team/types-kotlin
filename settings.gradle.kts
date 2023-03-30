rootProject.name = "types-kotlin"

pluginManagement {
    // TODO: remove after issue fix
    //  https://youtrack.jetbrains.com/issue/KT-57687
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }

    plugins {
        val kotlinVersion = extra["kotlin.version"] as String
        kotlin("multiplatform") version kotlinVersion

        val kfcVersion = extra["kfc.version"] as String
        id("io.github.turansky.kfc.library") version kfcVersion
        id("io.github.turansky.kfc.wrappers") version kfcVersion

        val seskarVersion = extra["seskar.version"] as String
        id("io.github.turansky.seskar") version seskarVersion
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

include("actions-kotlin")
include("browser-kotlin")
include("cesium-kotlin")
include("cesium-kotlin-nm")
include("csstype-kotlin")
include("node-kotlin")
include("popper-kotlin")
include("react-kotlin")
include("react-query-kotlin")
include("react-table-kotlin")
include("react-virtual-kotlin")
include("typescript-kotlin")
