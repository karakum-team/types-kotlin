plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    `csstype-declarations`
}

dependencies {
    jsMainImplementation(npmv("csstype"))

    jsMainImplementation(wrappers("js"))
}
