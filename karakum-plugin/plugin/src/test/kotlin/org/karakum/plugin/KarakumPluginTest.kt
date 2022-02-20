package org.karakum.plugin

import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Test
import kotlin.test.assertNotNull

class KarakumPluginTest {
    @Test
    fun `plugin registers task`() {
        val project = ProjectBuilder.builder().build()
        project.plugins.apply("org.karakum.plugin")

        assertNotNull(project.tasks.findByName("greeting"))
    }
}
