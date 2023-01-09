plugins {
    kfc("library")
    seskar()
    `popper-declarations`
}

val popperVersion = property("popper.version") as String

dependencies {
    implementation(npm("@popperjs/core", popperVersion))

    implementation(enforcedPlatform(kotlinwBom()))
    implementation(kotlinw("browser"))
}
