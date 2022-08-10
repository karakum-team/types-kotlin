import org.gradle.api.Project

fun kotlinw(name: String): String =
    "org.jetbrains.kotlin-wrappers:kotlin-$name"

fun Project.kotlinwBom(): String =
    kotlinw("wrappers-bom:${version("kotlin-wrappers")}")

fun Project.kotlinxCoroutines(name: String): String =
    kotlinx("kotlinx-coroutines", name)

private fun Project.kotlinx(
    projectName: String,
    name: String,
): String =
    "org.jetbrains.kotlinx:$projectName-$name:${version(projectName)}"
