plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    // `actions-declarations`
}

dependencies {
    // implementation(npmv("@types/web"))

    implementation(wrappers("node"))
}
