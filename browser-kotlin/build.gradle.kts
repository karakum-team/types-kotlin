plugins {
    id("io.github.turansky.kfc.library")
    id("io.github.turansky.seskar")
    `browser-declarations`
}

val webrtcTypesVersion = property("webrtc-types.version") as String

dependencies {
    implementation(npmv("typescript"))
    implementation(npm("@types/webrtc", webrtcTypesVersion))

    implementation(enforcedPlatform(kotlinwBom()))
    implementation(kotlinw("js"))
}
