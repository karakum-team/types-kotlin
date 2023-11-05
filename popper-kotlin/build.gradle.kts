plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    `popper-declarations`
}

val popperVersion = property("popper.version") as String

dependencies {
    jsMainImplementation(npm("@popperjs/core", popperVersion))

    jsMainImplementation(wrappers("browser"))

    jsMainImplementation(seskarCore())
}
