plugins {
    id("io.github.turansky.kfc.library")
    id("io.github.turansky.seskar")
    `react-virtual-declarations`
}

val reactVirtualVersion = property("react-virtual.version") as String
val kotlinWrappersVersion = property("kotlin-wrappers.version") as String

dependencies {
    implementation(npm("@tanstack/react-virtual", reactVirtualVersion))

    implementation(enforcedPlatform("org.jetbrains.kotlin-wrappers:kotlin-wrappers-bom:$kotlinWrappersVersion"))
    implementation("org.jetbrains.kotlin-wrappers:kotlin-browser")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-core")
}
