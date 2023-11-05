plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    `cesium-declarations`
}

dependencies {
    jsMainImplementation(npmv("cesium"))

    jsMainImplementation(wrappers("browser"))

    jsMainImplementation(seskarCore())
}
