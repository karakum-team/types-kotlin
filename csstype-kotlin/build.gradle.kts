plugins {
    kfc("library")
    seskar()
    `csstype-declarations`
}

dependencies {
    implementation(npmv("csstype"))

    implementation(enforcedPlatform(kotlinwBom()))
    implementation(kotlinw("js"))
}
