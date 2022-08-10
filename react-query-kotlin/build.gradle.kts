plugins {
    id("io.github.turansky.kfc.library")
    id("io.github.turansky.seskar")
    `react-query-declarations`
}

dependencies {
    implementation(npmv("react-query"))

    implementation(enforcedPlatform(kotlinwBom()))
    implementation(kotlinw("browser"))
    implementation(kotlinw("react-core"))
}
