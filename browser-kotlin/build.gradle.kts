plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    `browser-declarations`
}

kotlin {
    sourceSets.configureEach {
        languageSettings.optIn("kotlin.ExperimentalStdlibApi")
    }
}

dependencies {
    jsMainImplementation(npmv("@types/web"))
    jsMainImplementation(npmv("@types/serviceworker"))
    jsMainImplementation(npmv("@types/audioworklet"))
    jsMainImplementation(npmv("typescript"))
    jsMainImplementation(npmv("@webref/events"))
    jsMainImplementation(npmv("@webref/idl"))

    jsMainImplementation(wrappers("cssom-core"))
    jsMainImplementation(wrappers("js"))

    jsMainImplementation(kotlinxCoroutines("core"))
}

val INTERNAL = setOf(
    "web/abort/internal",
)

val JS_INCLUDE = setOf(
    "js",
)

val WEB_INCLUDE = setOf(
    "web/abort",
    "web/assembly",
    "web/blob",
    "web/broadcast",
    "web/compression",
    "web/console",
    "web/crypto",
    "web/encoding",
    "web/errors/DOMException.kt",
    "web/events",
    "web/file/File.kt",
    "web/file/FilePropertyBag.kt",
    "web/file/FileReader.kt",
    "web/form/FormData.kt",
    "web/form/FormDataEntryValue.kt",
    "web/gpu",
    "web/http",
    "web/images",
    "web/messaging",
    "web/performance",
    "web/promise",
    "web/scheduling/queueMicrotask.kt",
    "web/scheduling/VoidFunction.kt",
    "web/serialization",
    "web/sockets",
    "web/time",
    "web/timers",
    "web/streams",
    "web/url",

    // markers
    "web/canvas/CanvasImageSource.kt",
    "web/gl/TexImageSource.kt",
)

val BROWSER_INCLUDE = setOf(
    "web",
    "web/file",
    "web/images/ImageBitmapRenderingContext.kt",
    "web/images/ImageBitmapRenderingContextSettings.kt",
    "web/performance/LargestContentfulPaint.kt",
)

enum class WrapperProject {
    JS,
    WEB,
    BROWSER,

    ;
}

fun getWrapperProject(path: String): WrapperProject? =
    when (path) {
        in INTERNAL -> null
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

val syncKotlinJs by tasks.creating(SyncWrappers::class) {
    from(generatedDir) {
        include(isFromWrapperProject(WrapperProject.JS))

        preserve {
            include("js/atomic/WaitAsyncResult.kt")
            include("js/atomic/WaitResult.kt")
            include("js/atomic/WaitStatus.kt")
            include("js/atomic/WaitSyncResult.kt")
        }
    }

    into(kotlinWrappersDir("kotlin-js"))
}

val syncKotlinWeb by tasks.creating(SyncWrappers::class) {
    from(generatedDir) {
        include(isFromWrapperProject(WrapperProject.WEB))
    }

    into(kotlinWrappersDir("kotlin-web"))
}

val syncKotlinBrowser by tasks.creating(SyncWrappers::class) {
    from(generatedDir) {
        include(isFromWrapperProject(WrapperProject.BROWSER))
    }

    into(kotlinWrappersDir("kotlin-browser"))
}

val syncWithWrappers by tasks.creating {
    dependsOn(syncKotlinJs)
    dependsOn(syncKotlinWeb)
    dependsOn(syncKotlinBrowser)
}
