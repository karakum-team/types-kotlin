plugins {
    id("io.github.turansky.kfc.library")
    id("io.github.turansky.seskar")
    `browser-declarations`
}

dependencies {
    implementation(npmv("typescript"))

    implementation(enforcedPlatform(kotlinwBom()))
    implementation(kotlinw("js"))
    implementation(kotlinw("web"))
}
