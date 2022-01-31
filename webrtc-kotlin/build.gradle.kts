plugins {
    id("com.github.turansky.kfc.library")
    `webrtc-declarations`
}

val webrtcTypesVersion = property("webrtc-types.version") as String

dependencies {
    implementation(npm("@types/webrtc", webrtcTypesVersion))
}
