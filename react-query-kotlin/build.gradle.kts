plugins {
    id("io.github.turansky.kfc.library")
    `react-query-declarations`
}

val kotlinWrappersVersion = property("kotlin-wrappers.version") as String

dependencies {
    implementation(npmv("react-query"))

    implementation(enforcedPlatform("org.jetbrains.kotlin-wrappers:kotlin-wrappers-bom:$kotlinWrappersVersion"))
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-core")
}
