plugins {
    id("io.github.turansky.kfc.library")
    `cesium-declarations`
}

dependencies {
    implementation(npmv("cesium"))
}
