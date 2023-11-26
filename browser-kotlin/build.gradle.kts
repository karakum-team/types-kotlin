plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    `browser-declarations`
}

dependencies {
    jsMainImplementation(npmv("@types/web"))
    jsMainImplementation(npmv("@types/serviceworker"))
    jsMainImplementation(npmv("typescript"))
    jsMainImplementation(npmv("@webref/idl"))

    jsMainImplementation(wrappers("cssom-core"))
    jsMainImplementation(wrappers("js"))
    jsMainImplementation(wrappers("web"))

    jsMainImplementation(seskarCore())
}

val JS_INCLUDE = setOf(
    "js",
)

val WEB_INCLUDE = setOf(
    "web/abort",
    "web/buffer/Blob.kt",
    "web/buffer/BlobPart.kt",
    "web/buffer/BlobPropertyBag.kt",
    "web/buffer/EndingType.kt",
    "web/compression",
    "web/console",
    "web/crypto",
    "web/encoding",
    "web/events",
    "web/file/File.kt",
    "web/file/FilePropertyBag.kt",
    "web/http",
    "web/messaging",
    "web/performance",
    "web/streams",
    "web/url",
    "webassembly",
)

val BROWSER_INCLUDE = setOf(
    "web",
    "web/buffer",
    "web/events/ProgressEvent.kt",
    "web/file",

    "webgl",
    "webrtc",
    "websockets",
    "webvtt",
)

enum class WrapperProject {
    JS,
    WEB,
    BROWSER,

    ;
}

fun getWrapperProject(path: String): WrapperProject? =
    when (path) {
        in JS_INCLUDE -> WrapperProject.JS
        in WEB_INCLUDE -> WrapperProject.WEB
        in BROWSER_INCLUDE -> WrapperProject.BROWSER
        else -> if ("/" in path) getWrapperProject(path.substringBeforeLast("/")) else null
    }

fun isDirFromWrapperProject(
    path: String,
    wp: WrapperProject,
): Boolean {
    val included = when (wp) {
        WrapperProject.JS -> JS_INCLUDE
        WrapperProject.WEB -> WEB_INCLUDE
        WrapperProject.BROWSER -> BROWSER_INCLUDE
    }

    if (path in included)
        return true

    if (included.any { it.startsWith("$path/") })
        return true

    val basePath = path.substringBefore("/", "")
    return basePath in included
}

fun isFromWrapperProject(wp: WrapperProject): Spec<FileTreeElement> {
    return Spec<FileTreeElement> { element ->
        val path = element.path
        if (element.isDirectory) {
            isDirFromWrapperProject(path, wp)
        } else {
            getWrapperProject(path) == wp
        }
    }
}

val syncKotlinWeb by tasks.creating(Sync::class) {
    val generatedDir = project.layout.projectDirectory.dir("src/jsMain/kotlin")

    val kotlinWrappersDir = project.rootProject.layout.projectDirectory.dir("../kotlin-wrappers")
    val webDir = kotlinWrappersDir.dir("kotlin-web/src/jsMain/generated")

    from(generatedDir) {
        include(isFromWrapperProject(WrapperProject.WEB))
    }

    into(webDir.asFile)
}

val syncKotlinBrowser by tasks.creating(Sync::class) {
    val generatedDir = project.layout.projectDirectory.dir("src/jsMain/kotlin")

    val kotlinWrappersDir = project.rootProject.layout.projectDirectory.dir("../kotlin-wrappers")
    val browserDir = kotlinWrappersDir.dir("kotlin-browser/src/jsMain/generated")

    from(generatedDir) {
        include(isFromWrapperProject(WrapperProject.BROWSER))
    }

    into(browserDir.asFile)
}

val syncWithWrappers by tasks.creating {
    dependsOn(syncKotlinWeb)
    dependsOn(syncKotlinBrowser)
}
