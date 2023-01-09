plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    `popper-declarations`
}

val popperVersion = property("popper.version") as String

dependencies {
    implementation(npm("@popperjs/core", popperVersion))

    implementation(wrappers("browser"))
}
