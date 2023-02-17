plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    `react-virtual-declarations`
}

val reactVirtualVersion = property("react-virtual.version") as String

dependencies {
    jsMainImplementation(npm("@tanstack/react-virtual", reactVirtualVersion))

    jsMainImplementation(wrappers("browser"))
    jsMainImplementation(wrappers("react-core"))
}
