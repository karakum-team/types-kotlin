plugins {
    id("io.github.turansky.kfc.library")
    id("io.github.turansky.seskar")
    `react-table-declarations`
}

val reactTableVersion = property("react-table.version") as String
val kotlinWrappersVersion = property("kotlin-wrappers.version") as String

dependencies {
    implementation(npm("@tanstack/react-table", reactTableVersion))

    implementation(enforcedPlatform("org.jetbrains.kotlin-wrappers:kotlin-wrappers-bom:$kotlinWrappersVersion"))
    implementation("org.jetbrains.kotlin-wrappers:kotlin-browser")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-core")
}
