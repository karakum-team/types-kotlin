package team.karakum.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class KarakumGradlePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        // Register a task
        project.tasks.register("convert") { task ->
            task.group = KARAKUM_GRADLE_PLUGIN_GROUP

            task.doLast {
                println("Hello from plugin 'team.karakum.converter'")
            }
        }
    }
}
