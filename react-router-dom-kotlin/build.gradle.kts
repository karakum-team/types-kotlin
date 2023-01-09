plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    `react-router-dom-declarations`
}

dependencies {
    implementation(npmv("history"))
    implementation(npmv("react-router-dom"))

    implementation(wrappers("react-dom"))
}
