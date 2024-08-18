plugins {
    alias(libs.plugins.kfc.library)
    alias(libs.plugins.seskar)
    `react-table-declarations`
}

val reactTableVersion = property("react-table.version") as String

dependencies {
    jsMainImplementation(npm("@tanstack/react-table", reactTableVersion))

    jsMainImplementation(libs.wrappers.browser)
    jsMainImplementation(libs.wrappers.react.core)
}
