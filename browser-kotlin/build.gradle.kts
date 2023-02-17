plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    `browser-declarations`
}

dependencies {
    jsMainImplementation(npmv("@types/web"))
    jsMainImplementation(npmv("@types/serviceworker"))
    jsMainImplementation(npmv("typescript"))
    jsMainImplementation(npmv("@webref/idl"))

    jsMainImplementation(wrappers("js"))
    jsMainImplementation(wrappers("web"))
}
