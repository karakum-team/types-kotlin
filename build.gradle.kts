plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.jsPlainObjects) apply false
}

tasks.wrapper {
    gradleVersion = "8.11.1"
}

// TODO: remove after TanStack Virtual migration on React 19
plugins.withType<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin> {
    the<org.jetbrains.kotlin.gradle.targets.js.npm.NpmExtension>().apply {
        override("react", "^19.0.0")
        override("react-dom", "^19.0.0")
    }
}
