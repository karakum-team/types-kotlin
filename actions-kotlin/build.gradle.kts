plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    `actions-declarations`
}

dependencies {
    implementation(npmv("@actions/artifact"))
    implementation(npmv("@actions/cache"))
    implementation(npmv("@actions/core"))
    implementation(npmv("@actions/exec"))
    // implementation(npmv("@actions/github"))
    implementation(npmv("@actions/glob"))
    implementation(npmv("@actions/http-client"))
    implementation(npmv("@actions/io"))
    implementation(npmv("@actions/tool-cache"))

    implementation(wrappers("node"))
}
