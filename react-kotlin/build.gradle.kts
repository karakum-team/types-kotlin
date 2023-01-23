plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    `react-declarations`
}

dependencies {
    implementation(npmv("@types/react"))

    implementation(wrappers("browser"))
    implementation(wrappers("react-core"))
}
