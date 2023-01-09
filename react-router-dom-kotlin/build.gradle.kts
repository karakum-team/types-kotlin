plugins {
    kfc("library")
    seskar()
    `react-router-dom-declarations`
}

dependencies {
    implementation(npmv("history"))
    implementation(npmv("react-router-dom"))

    implementation(enforcedPlatform(kotlinwBom()))
    implementation(kotlinw("react-dom"))
}
