plugins {
    id("io.github.turansky.kfc.library")
    id("io.github.turansky.seskar")
    `react-router-dom-declarations`
}

dependencies {
    implementation(npmv("history"))
    implementation(npmv("react-router-dom"))

    implementation(enforcedPlatform(kotlinwBom()))
    implementation(kotlinw("react-dom"))
}
