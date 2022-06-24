plugins {
    id("io.github.turansky.kfc.library")
    id("io.github.turansky.seskar")
    `cesium-declarations`
}

dependencies {
    implementation(npmv("cesium"))
}
