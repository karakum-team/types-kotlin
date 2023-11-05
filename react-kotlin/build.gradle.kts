plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    `react-declarations`
}

dependencies {
    jsMainImplementation(npmv("@types/react"))

    jsMainImplementation(wrappers("browser"))
    jsMainImplementation(wrappers("react-core"))

    jsMainImplementation(seskarCore())
}
