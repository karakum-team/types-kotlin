plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    `csstype-declarations`
}

dependencies {
    implementation(npmv("csstype"))

    implementation(wrappers("js"))
}
