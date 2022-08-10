plugins {
    id("io.github.turansky.kfc.library")
    id("io.github.turansky.seskar")
    `webrtc-declarations`
}

val webrtcTypesVersion = property("webrtc-types.version") as String

dependencies {
    implementation(npm("@types/webrtc", webrtcTypesVersion))

    implementation(enforcedPlatform(kotlinwBom()))
    implementation(kotlinw("js"))
}
