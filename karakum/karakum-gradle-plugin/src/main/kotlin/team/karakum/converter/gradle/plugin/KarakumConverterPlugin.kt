package team.karakum.converter.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.targets
import org.jetbrains.kotlin.gradle.targets.js.KotlinJsTarget
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsExec

class KarakumConverterPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val jsResource = requireNotNull(KarakumConverterPlugin::class.java.getResource("/karakum-converter.js"))
        val executableJs = project.layout.buildDirectory.file("karakum/karakum-converter.js").get().asFile

        val kotlinJsTarget = project.kotlinExtension.targets.firstNotNullOf { it as? KotlinJsTarget }
        val kotlinJsCompilation =
            kotlinJsTarget.compilations.first { it.name == KotlinCompilation.MAIN_COMPILATION_NAME }

        project.tasks.register("prepareExecutableJs") { task ->
            task.group = KARAKUM_GRADLE_PLUGIN_GROUP

            task.doLast {
                executableJs.parentFile.mkdir()
                executableJs.writeText(jsResource.readText())
            }
        }

        NodeJsExec.create(kotlinJsCompilation, "convert") {
            group = KARAKUM_GRADLE_PLUGIN_GROUP

            dependsOn("prepareExecutableJs")

            inputFileProperty.set(executableJs)

            doLast {
                println("Hello from plugin 'team.karakum.converter'")
            }
        }
    }
}
