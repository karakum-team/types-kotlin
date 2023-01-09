plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    `react-virtual-declarations`
}

val reactVirtualVersion = property("react-virtual.version") as String

dependencies {
    implementation(npm("@tanstack/react-virtual", reactVirtualVersion))

    implementation(wrappers("browser"))
    implementation(wrappers("react-core"))
}
