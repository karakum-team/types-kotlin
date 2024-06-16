import org.gradle.api.file.Directory
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.Sync
import java.io.File

open class SyncWrappers : Sync() {
    @get:Internal
    val generatedDir: Directory
        get() = project.layout.projectDirectory.dir("src/jsMain/kotlin")

    fun kotlinWrappersDir(
        projectName: String,
    ): File =
        project.rootProject.layout.projectDirectory
            .dir("../kotlin-wrappers")
            .dir(projectName)
            .dir("src/jsMain/generated")
            .asFile
}
