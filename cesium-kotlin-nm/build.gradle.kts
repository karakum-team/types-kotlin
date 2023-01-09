plugins {
    kfc("library")
    seskar()
    `cesium-declarations`
}

dependencies {
    implementation(npmv("cesium"))

    implementation(enforcedPlatform(kotlinwBom()))
    implementation(kotlinw("browser"))
}
