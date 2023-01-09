plugins {
    kfc("library")
    kfc("wrappers")
    seskar()
    `react-table-declarations`
}

val reactTableVersion = property("react-table.version") as String

dependencies {
    implementation(npm("@tanstack/react-table", reactTableVersion))

    implementation(wrappers("browser"))
    implementation(wrappers("react-core"))
}
