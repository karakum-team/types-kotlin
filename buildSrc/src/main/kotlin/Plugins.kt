import org.gradle.plugin.use.PluginDependenciesSpec

fun PluginDependenciesSpec.kfc(name: String) {
    id("io.github.turansky.kfc.$name")
}

fun PluginDependenciesSpec.seskar() {
    id("io.github.turansky.seskar")
}
