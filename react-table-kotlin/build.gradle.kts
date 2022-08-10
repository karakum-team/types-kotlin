plugins {
    id("io.github.turansky.kfc.library")
    id("io.github.turansky.seskar")
    `react-table-declarations`
}

val reactTableVersion = property("react-table.version") as String

dependencies {
    implementation(npm("@tanstack/react-table", reactTableVersion))

    implementation(enforcedPlatform(kotlinwBom()))
    implementation(kotlinw("browser"))
    implementation(kotlinw("react-core"))
}
