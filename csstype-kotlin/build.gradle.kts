plugins {
    id("io.github.turansky.kfc.library")
    id("io.github.turansky.seskar")
    `csstype-declarations`
}

dependencies {
    implementation(npmv("csstype"))

    implementation(enforcedPlatform(kotlinwBom()))
    implementation(kotlinw("js"))
}
