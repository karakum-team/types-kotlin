import org.gradle.api.file.Directory
import org.gradle.api.tasks.Sync

class SyncWrappers : Sync() {
    val generatedDir: Directory
        get() = project.layout.projectDirectory.dir("src/jsMain/kotlin")
}
