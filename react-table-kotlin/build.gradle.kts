plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    `react-table-declarations`
}

val reactTableVersion = property("react-table.version") as String

dependencies {
    jsMainImplementation(npm("@tanstack/react-table", reactTableVersion))

    jsMainImplementation(wrappers("browser"))
    jsMainImplementation(wrappers("react-core"))
}
