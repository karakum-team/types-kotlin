plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    `cesium-declarations`
}

dependencies {
    implementation(npmv("cesium"))

    implementation(wrappers("browser"))
}
