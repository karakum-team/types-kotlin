plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    `react-query-declarations`
}

val reactQueryVersion = property("react-query.version") as String

dependencies {
    implementation(npm("@tanstack/react-query", reactQueryVersion))

    implementation(wrappers("web"))
    implementation(wrappers("react-core"))
}
