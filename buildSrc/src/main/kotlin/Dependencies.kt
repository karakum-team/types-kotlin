import org.gradle.api.Project

fun Project.kotlinxCoroutines(name: String): String =
    kotlinx("kotlinx-coroutines", name)

private fun Project.kotlinx(
    projectName: String,
    name: String,
): String =
    "org.jetbrains.kotlinx:$projectName-$name:${version(projectName)}"
