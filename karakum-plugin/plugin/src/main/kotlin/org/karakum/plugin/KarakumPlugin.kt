package org.karakum.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class KarakumPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        // Register a task
        project.tasks.register("greeting") { task ->
            task.doLast {
                println("Hello from plugin 'org.karakum.plugin'")
            }
        }
    }
}
