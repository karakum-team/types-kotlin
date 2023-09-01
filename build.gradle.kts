import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension

plugins {
    kotlin("multiplatform") apply false
}

tasks.wrapper {
    gradleVersion = "8.3"
}

plugins.withType<YarnPlugin> {
    the<YarnRootExtension>().apply {
        resolution("@types/node", version("node-types"))
    }
}
