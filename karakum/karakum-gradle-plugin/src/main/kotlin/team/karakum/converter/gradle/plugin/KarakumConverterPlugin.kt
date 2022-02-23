package team.karakum.converter.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Copy
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.targets
import org.jetbrains.kotlin.gradle.targets.js.KotlinJsTarget
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsExec

class KarakumConverterPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val zipResource = requireNotNull(KarakumConverterPlugin::class.java.getResource("/karakum.zip"))
        val zipFile = project.layout.buildDirectory.dir("tmp/karakum/karakum.zip").get()
        val embeddedJsLibrary = project.layout.buildDirectory.dir("karakum").get()

        val kotlinJsTarget = project.kotlinExtension.targets.firstNotNullOf { it as? KotlinJsTarget }
        val kotlinJsCompilation =
            kotlinJsTarget.compilations.first { it.name == KotlinCompilation.MAIN_COMPILATION_NAME }

        project.tasks.register("prepareEmbeddedJsLibrary") { task ->
            task.group = KARAKUM_GRADLE_PLUGIN_GROUP

            task.doLast {
                zipFile.asFile.parentFile.mkdirs()
                zipFile.asFile.writeBytes(zipResource.readBytes())
            }
        }

        project.tasks.register("unpackEmbeddedJsLibrary", Copy::class.java) { task ->
            task.group = KARAKUM_GRADLE_PLUGIN_GROUP

            task.dependsOn("prepareEmbeddedJsLibrary")

            task.from(project.zipTree(zipFile))
            task.into(embeddedJsLibrary)
        }

        NodeJsExec.create(kotlinJsCompilation, "convert") {
            group = KARAKUM_GRADLE_PLUGIN_GROUP

            dependsOn("unpackEmbeddedJsLibrary")

            inputFileProperty.set(embeddedJsLibrary.file("karakum-converter.js"))

            doLast {
                println("Hello from plugin 'team.karakum.converter'")
            }
        }
    }
}
