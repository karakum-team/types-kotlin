plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    `react-query-declarations`
}

val reactQueryVersion = property("react-query.version") as String

dependencies {
    jsMainImplementation(npm("@tanstack/react-query", reactQueryVersion))

    jsMainImplementation(wrappers("web"))
    jsMainImplementation(wrappers("react-core"))
}
