plugins {
    kfc("library")
    seskar()
    `browser-declarations`
}

dependencies {
    implementation(npmv("typescript"))
    implementation(npmv("@webref/idl"))

    implementation(enforcedPlatform(kotlinwBom()))
    implementation(kotlinw("js"))
    implementation(kotlinw("web"))
}
